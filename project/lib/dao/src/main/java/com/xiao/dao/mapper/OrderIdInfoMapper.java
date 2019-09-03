package com.xiao.dao.mapper;

import com.xiao.dao.entity.OrderIdInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderIdInfoMapper {
    @Select({
            "select * from order_id_info where order_id = #{orderId}"
    })
    OrderIdInfo selectOrderIdInfoByOrderId(String orderId);


    @Update({
            "update order_id_info set status = #{status} where order_id = #{orderId}"
    })
    int updateStatusByOrderId(@Param("orderId") String orderId, @Param("status") int status);

    @Insert({
            "insert into order_id_info values (#{info.orderId}, #{info.status})"
    })
    int insert(@Param("info") OrderIdInfo info);

}