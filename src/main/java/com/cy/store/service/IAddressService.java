package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;

import java.util.List;

public interface IAddressService {

    /**
     * 新增地址
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 显示收获地址列表
     */
    List<Address> getByUid(Integer uid);

}
