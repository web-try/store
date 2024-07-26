package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;

import java.util.List;

public interface AddressMapper {
    Integer insert(Address address);

    Integer countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Address findByAid(Integer aid);

    Integer updateNonDefault(Integer uid);

    Integer updateDefaultByAid(Integer aid);

    Integer deleteByAid(Integer aid);
}
