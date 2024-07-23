package com.cy.store.service;

import com.cy.store.entity.Address;

public interface IAddressService {

    /**
     * 新增地址
     */
    void addNewAddress(Integer uid, String username, Address address);
}
