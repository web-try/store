package com.cy.store.service;

import com.cy.store.entity.Order;

public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
