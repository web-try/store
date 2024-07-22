package com.cy.store.enums;

import com.cy.store.service.ex.InsertException;

public enum AppHttpCodeEnum {
    SUCCESS(200, "操作成功"),

    NULLNAMEORPASSWORD(999,"没有输入密码或者用户名"),

    USERNAMEDUPLICATE(4000,"用户名被占用"),

    INSERT(5000,"注册时发生未知异常请重新注册"),

    USERNOTFOUND(5001,"没有该用户"),

    PASSWORDNOTMATCH(5002,"密码不匹配"),

    FILEEMPTY(6000,"上传文件为空"),

    FILESIZE(6001,"上传的文件的大小超出了限制"),

    FILETYPE(6002,"上传的文件类型超出了限制"),

    FILESTATE(6003,"上传的文件状态异常"),

    FILEUPLOADIO(6004,"上传文件时读写异常");

    private int code;

    private String message;

    AppHttpCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
