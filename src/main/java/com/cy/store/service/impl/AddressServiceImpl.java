package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
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

    @Override
    public void setDefault(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("没有这个地址修改异常");
        }

        if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问数据");
        }

        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows < 1) {
            throw new UpdateException("更新异常");
        }

        rows = addressMapper.updateDefaultByAid(aid);

        if (rows != 1) {
            throw new UpdateException("更新异常");
        }

    }

    @Override
    public void delete(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("没有这个地址修改异常");
        }

        if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问数据");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if(rows != 1) {
            throw new DeleteException("删除数据时产生错误");
        }

        //如果删除的是默认数据就取出来一条重新设置成默认数据
        if(result.getIsDefault() == 1) {
            List<Address> addressList = addressMapper.findByUid(uid);
            if(addressList.isEmpty()) {
                return;
            }
            Address address = addressList.get(0);
            rows = addressMapper.updateDefaultByAid(address.getAid());
            if(rows != 1) {
                throw new UpdateException("设置新的默认地址时产生错误");
            }
        }
    }
}
