package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.sql.rowset.serial.SerialException;

public class BaseController {
    public static final int OK = 200;

    public static final int nullNameOrPassword = 999;
    /**
     * ExceptionHandler此注解作用是当出现异常时会统一拦截到此注解的方法中
     * 出现异常时此方法充当返回值直接返回给前端
     */
    @ExceptionHandler(SerialException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();
        if(e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时发生未知异常请重新注册");
        }else if (e instanceof UserNotFoundException || e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("注册时发生未知异常请重新注册");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("注册时发生未知异常请重新注册");
        }
        return result;
    }

    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

}
