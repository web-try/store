package com.cy.store.service;

import com.cy.store.Vo.CartVo;

import java.util.List;

public interface ICartService {
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    List<CartVo> getVoByUid(Integer uid);
}
