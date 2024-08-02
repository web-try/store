package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     * 热销前四商品展示
     */
    List<Product> findHotList();

    /**
     * 热销界面跳转具体逻辑
     */
    Product findById(Integer id);

    /**
     * 新到好货界面跳转具体逻辑
     */
    List<Product> findNewList();
}
