package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.util.XssUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {

        User byUsername = userMapper.findByUsername(user.getUsername());
        if (byUsername != null) {
            throw new UsernameDuplicateException("用户名已存在");
        }
        /**
         * 获取盐值进行md5的三次加密之后将生成好的密码赋值给User
         */
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setPassword(XssUntil.getMD5Password(user.getPassword(), salt));
        /**
         * 将其他信息封装进user
         */
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer insert = userMapper.insert(user);

        if(insert != 1) {
            throw new InsertException("插入数据时出现未知错误");
        }

    }
}
