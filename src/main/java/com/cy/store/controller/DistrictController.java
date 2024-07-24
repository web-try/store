package com.cy.store.controller;

import com.cy.store.entity.District;
import com.cy.store.enums.AppHttpCodeEnum;
import com.cy.store.service.IDistrictService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent) {
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(),districtService.getByParent(parent));
    }
}
