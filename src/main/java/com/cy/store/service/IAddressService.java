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

    /**
     * 修改某个用户的某条收获地址为默认收货地址
     */
    void setDefault(Integer aid, Integer uid);

    /**
     * 删除地址数据
     */
    void delete(Integer aid, Integer uid);
}
