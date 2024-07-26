package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.enums.AppHttpCodeEnum;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address) {

        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        return new JsonResult(AppHttpCodeEnum.SUCCESS.getCode());
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {

        Integer uid = getuidFromSession(session);
        List<Address> addressList = addressService.getByUid(uid);
        return new JsonResult(AppHttpCodeEnum.SUCCESS.getCode(), addressList);
    }

    //@PathVariable接收地址参数
    @RequestMapping("/{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable Integer aid, HttpSession session) {
        addressService.setDefault(aid, getuidFromSession(session));
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode());
    }

}
