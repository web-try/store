package com.cy.store.controller;

import com.cy.store.Vo.CartVo;
import com.cy.store.enums.AppHttpCodeEnum;
import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        cartService.addToCart(getuidFromSession(session),pid,amount,getUsernameFromSession(session));
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode());
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVo>> getVoByUid(HttpSession session) {
        System.out.println("1111");
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(),cartService.getVoByUid(getuidFromSession(session)));
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable Integer cid, HttpSession session) {
        Integer data = cartService.addNum(cid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(),data);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVo>> getVoByCid(Integer[] cids, HttpSession session) {
        List<CartVo> data = cartService.getVoByCid(getuidFromSession(session),cids);
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(),data);
    }
}
