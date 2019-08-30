package com.xiao.ordercenter.controller;

import com.xiao.ordercenter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCenterController {
    @Autowired
    OrderService orderService;

    @GetMapping("/send")
    public void send(){
        orderService.addOrderAndDispatch();
    }
}
