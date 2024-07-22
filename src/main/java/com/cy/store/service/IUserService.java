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

    /**
     * 根据uid查找数据返回前端
     */
    User getByUid(Integer uid);

    /**
     * 修改个人资料
     */
    void  changeInfo(Integer uid, String username, User user);

    /**
     * 修改头像
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
