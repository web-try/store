package com.cy.store.service;

import com.cy.store.entity.User;

/**用户模块业务层接口*/
public interface IUserService {
    /**
     * 注册功能
     */
    void reg(User user);

    /**
     * 登录功能
     */
    User login(String username, String password);

    /**
     * 修改密码
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);
}
