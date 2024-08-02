package com.cy.store.service;

import com.cy.store.Vo.CartVo;

import java.util.List;

public interface ICartService {

    /**
     * 将商品添加到购物车
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    /**
     * 根据用户id查询购物车数据
     */
    List<CartVo> getVoByUid(Integer uid);

    /**
     * 更新用户购物车中商品的数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    Integer minusNum(Integer cid, Integer uid, String username);

    /**
     * 显示勾选购物车数据
     */
    List<CartVo> getVoByCid(Integer uid, Integer[] cids);

    /**
     * 删除购物车某商品
     */
    void deleteNum(Integer cid);
}
