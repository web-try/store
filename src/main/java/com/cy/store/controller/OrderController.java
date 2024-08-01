package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.enums.AppHttpCodeEnum;
import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("orders")
@RestController
public class OrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {

        Order order = orderService.create(aid, cids, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(),order);
    }
}
