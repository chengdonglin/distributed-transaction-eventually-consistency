package com.dmai.transaction.order.controller;

import com.dmai.transaction.order.req.OrderReq;
import com.dmai.transaction.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 11:38:33
 */
@RestController
@RequestMapping("tx")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("submit")
    public String transfer(@RequestBody OrderReq orderReq) {
        orderService.submitOrder(orderReq.getProductId(),orderReq.getPayCount());
        return "下单成功";
    }
}
