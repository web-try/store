package com.cy.store.mapper;

import com.cy.store.Vo.CartVo;
import com.cy.store.entity.Cart;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    Integer insert(Cart cart);

    Integer updateNumByCid(Integer cid, Integer num, Date date);

    Cart findByUidAndPid(Integer uid, Integer pid);

    List<CartVo> findByUid(Integer uid);
}