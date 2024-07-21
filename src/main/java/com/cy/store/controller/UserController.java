package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 注册模块
     */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
//        System.out.println(user);
        if ("".equals(user.getUsername()) || "".equals(user.getPassword())) {
            return new JsonResult<>(nullNameOrPassword);
        }
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * @param username 前端传来的用户名字
     * @param password 前端传来的用户密码
     * @param session  全局session对象springmvc自动维护
     */
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        System.out.println(getUsernameFromSession(session));
        System.out.println(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String name = getUsernameFromSession(session);
        userService.changePassword(uid, name, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User date = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, date);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }

}
