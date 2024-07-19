package com.cy.store.service;

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
        User test01 = userService.login("test01", "123");
        System.out.println(test01);
    }
}
