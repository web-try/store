package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if(count == 20) {
            throw new AddressCountLimitException("用户地址太多了");
        }

        //将根据code查到的省市区的名字放入表单
        address.setProvinceName(districtMapper.findNameByCode(address.getProvinceCode()));
        address.setCityName(districtMapper.findNameByCode(address.getCityCode()));
        address.setAreaName(districtMapper.findNameByCode(address.getAreaCode()));

        address.setUid(uid);
        //如果是第零条记录设置为默认记录
        address.setIsDefault(count == 0 ? 1 : 0);

        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        Integer row = addressMapper.insert(address);

        if (row != 1) {
            throw new InsertException("插入用户地址数据时出现未知错误");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return addressMapper.findByUid(uid);
    }
}
