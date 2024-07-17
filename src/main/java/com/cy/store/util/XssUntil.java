package com.cy.store.util;

import org.springframework.util.DigestUtils;

public class XssUntil {
    private XssUntil(){}

    /**
     *
     * @param password 用户密码
     * @param salt 随机生成的UUID
     * @return 返回加密过后的密码
     */
    public static String getMD5Password(String password,String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
