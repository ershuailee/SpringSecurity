package com.example.springsecurity.enums;

import lombok.Getter;

@Getter
public enum BusinessErrorCodes {

    /**
     * 业务异常
     */
    DEFAULT_BUSINESS_ERROR("defaultError", "业务异常"),

    /**
     * 数据处理异常
     */
    INSERT_FAILED("insertFailed", "插入数据失败"),
    DELETE_FAILED("deleteFailed", "删除数据失败"),
    UPDATE_FAILED("updateFailed", "更新数据失败"),

    /**
     * 用户异常
     */
    USER_NAME_HAS_EXISTED("userNameHasExisted", "用户名称已存在"),
    USER_NOT_EXISTS("userNotExists", "用户不存在"),
    PASSWORD_ERROR("passwordError", "用户名或密码错误"),
    ACCOUNT_LOCKED("accountLocked", "账号锁定"),
    INVALID_TOKEN("invalidToken", "登陆失效"),
    ;

    private final String code;
    private final String message;

    BusinessErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
