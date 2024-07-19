package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    /**
     * 注册模块
     */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
        System.out.println(user);
        if("".equals(user.getUsername()) || "".equals(user.getPassword())) {
            return new JsonResult<>(nullNameOrPassword);
        }
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password) {
        User data = userService.login(username, password);
        return new JsonResult<User>(OK,data);
    }

}
