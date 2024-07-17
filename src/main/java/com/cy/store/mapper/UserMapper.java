package com.cy.store.mapper;


import com.cy.store.entity.User;

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
}
