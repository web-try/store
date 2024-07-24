package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IDistrictService districtService;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("xss");
        user.setPassword("123");
        try {
            userService.reg(user);
            System.out.println("ok");
        }catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
        }
    }

    @Test
    public void test1() {
        System.out.println(districtService.getByParent("86").toString());
    }

    @Test
    public void test2() {
        userService.changePassword(17, "test01","333","123");
    }
}
