package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
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

    @Override
    public User login(String username, String password) {

        User result = userMapper.findByUsername(username);
        if(result == null && result.getIsDelete() == 1) {
            throw new UserNotFoundException("没有该用户");
        }

        //传入的密码
        String salt = result.getSalt();
        String newPassword = XssUntil.getMD5Password(password, salt);

        if(!result.getPassword().equals(newPassword)) {
            throw new PasswordNotMatchException("密码不对");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {

        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("没有该用户");
        }
        
        String password = XssUntil.getMD5Password(result.getPassword(), result.getSalt());
        if (password.equals(oldPassword)) {
            throw new PasswordNotMatchException("密码错误");
        }

        String md5Password = XssUntil.getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePassword(uid, md5Password, username, new Date());
        if(rows != 1) {
            throw new UpdateException("修改密码时遇到错误");
        }
    }


}
