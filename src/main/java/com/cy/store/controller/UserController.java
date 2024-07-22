package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.enums.AppHttpCodeEnum;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 注册模块
     */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
//        System.out.println(user);
        if ("".equals(user.getUsername()) || "".equals(user.getPassword())) {
            return new JsonResult<>(AppHttpCodeEnum.NULLNAMEORPASSWORD.getCode());
        }
        userService.reg(user);
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode());
    }

    /**
     * 登录模块
     * @param username 前端传来的用户名字
     * @param password 前端传来的用户密码
     * @param session  全局session对象springmvc自动维护
     */
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        System.out.println(getUsernameFromSession(session));
        System.out.println(getuidFromSession(session));
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(), data);
    }

    /**
     *修改密码
     */
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String name = getUsernameFromSession(session);
        userService.changePassword(uid, name, oldPassword, newPassword);
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode());
    }

    /**
     * 获取登录人信息
     */
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User date = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(), date);
    }

    /**
     *修改个人资料
     */
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode());
    }

    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }


    /**
     * 上传头像
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(MultipartFile file, HttpSession session) {

        // 判断上传的文件是否为空
        if(file.isEmpty()) {
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：" + AVATAR_TYPES);
        }

        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        //获取头像名字
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀名
        int lastIndex = originalFilename.lastIndexOf(".");

        //获取文件名后缀并将后缀名和随机生成的UUID字符串拼接防止头像文件名重复
        String suffix = "";
        if(lastIndex > 0) {
            suffix = originalFilename.substring(lastIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        File dest = new File(dir, filename);
        //复制文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重新尝试");
        }

        String avatar = "/upload/" +filename;
        userService.changeAvatar(getuidFromSession(session), avatar, getUsernameFromSession(session));

        return new JsonResult<>(AppHttpCodeEnum.SUCCESS.getCode(),avatar);
    }
}
