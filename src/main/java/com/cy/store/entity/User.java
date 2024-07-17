package com.cy.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/** 用户实体类 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

    //'用户id',
    private Integer uid;

    //'用户昵称',
    private String username;

    //'用户密码',
    private String password;

    //'用户盐值',
    private String salt;

    //'电话号码'
    private String phone;

    // '电子邮箱'
    private String email;

    //'性别:0-女，1-男'
    private Integer gender;

    //头像
    private String avatar;

    //'是否删除：0-未删除，1-已删除'
    private Integer isDelete;

}
