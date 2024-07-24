package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.enums.AppHttpCodeEnum;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    //public static final int nullNameOrPassword = 999;
    /**
     * ExceptionHandler此注解作用是当出现异常时会统一拦截到此注解的方法中
     * 出现异常时此方法充当返回值直接返回给前端
     */
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();
        if(e instanceof UsernameDuplicateException) {
            result.setState(AppHttpCodeEnum.USERNAMEDUPLICATE.getCode());
            result.setMessage(AppHttpCodeEnum.USERNAMEDUPLICATE.getMessage());
        }else if (e instanceof InsertException) {
            result.setState(AppHttpCodeEnum.INSERT.getCode());
            result.setMessage(AppHttpCodeEnum.INSERT.getMessage());
        }else if (e instanceof UserNotFoundException) {
            result.setState(AppHttpCodeEnum.USERNOTFOUND.getCode());
            result.setMessage(AppHttpCodeEnum.USERNOTFOUND.getMessage());
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(AppHttpCodeEnum.PASSWORDNOTMATCH.getCode());
            result.setMessage(AppHttpCodeEnum.PASSWORDNOTMATCH.getMessage());
        }else if (e instanceof AddressCountLimitException) {
            result.setState(AppHttpCodeEnum.AddressCountLimit.getCode());
            result.setMessage(AppHttpCodeEnum.AddressCountLimit.getMessage());
        } else if (e instanceof UpdateException) {
            result.setState(AppHttpCodeEnum.UPDATE.getCode());
            result.setMessage(AppHttpCodeEnum.UPDATE.getMessage());
        } else if (e instanceof FileEmptyException) {
            result.setState(AppHttpCodeEnum.FILEEMPTY.getCode());
            result.setMessage(AppHttpCodeEnum.FILEEMPTY.getMessage());
        } else if (e instanceof FileSizeException) {
            result.setState(AppHttpCodeEnum.FILESIZE.getCode());
            result.setMessage(AppHttpCodeEnum.FILESIZE.getMessage());
        } else if (e instanceof FileTypeException) {
            result.setState(AppHttpCodeEnum.FILETYPE.getCode());
            result.setMessage(AppHttpCodeEnum.FILETYPE.getMessage());
        } else if (e instanceof FileStateException) {
            result.setState(AppHttpCodeEnum.FILESTATE.getCode());
            result.setMessage(AppHttpCodeEnum.FILESTATE.getMessage());
        } else if (e instanceof FileUploadIOException) {
            result.setState(AppHttpCodeEnum.FILEUPLOADIO.getCode());
            result.setMessage(AppHttpCodeEnum.FILEUPLOADIO.getMessage());
        }
        return result;
    }

    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

}
