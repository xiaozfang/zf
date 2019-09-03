package com.xiao.ordercenter.controller;

import com.xiao.common.response.BaseDataResponse;
import com.xiao.common.response.BaseResponse;
import com.xiao.common.util.StringUtils;
import com.xiao.ordercenter.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCenterController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("用户下单")
    @GetMapping("/createOrder")
    public BaseDataResponse<String> createOrder(){
        // 15分钟内支付有效
        BaseDataResponse<String> response = new BaseDataResponse<>();
        String orderId = orderService.createOrder();
        if (StringUtils.isEmptyOrNull(orderId)){
            response.setData("").fail("创建订单失败");
        }
        return response.setData(orderId).success("创建成功");
    }

    @ApiOperation("用户取消下单")
    @GetMapping("/cancelOrder")
    public BaseResponse cancelOrder(@ApiParam("订单号") @RequestParam("orderId") String orderId){
        return orderService.cancelOrder(orderId);
    }

    @ApiOperation("用户支付费用")
    @GetMapping("/payOrder")
    public BaseResponse payOrder(@ApiParam("订单号") @RequestParam("orderId") String orderId){
        return orderService.payOrder(orderId);
    }
}
