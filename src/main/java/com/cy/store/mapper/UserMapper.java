package com.cy.store.mapper;


import com.cy.store.entity.User;

import java.util.Date;

//用户模块的Mapper接口
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据，如果没有找到则返回null
     */
    User findByUsername(String username);

    /**
     * 根据ID查询用户数据
     * @param uid 用户名
     * @return 用户数据，如果没有找到则返回null
     */
    User findByUid(Integer uid);

    /**
     * 修改密码
     * @param uid 用户id
     * @param password 修改的新密码
     * @param modifiedUser 修改的人
     * @param modifiedTime 修改时间
     */
    Integer updatePassword(Integer uid,
                           String password,
                           String modifiedUser,
                           Date modifiedTime);


}
