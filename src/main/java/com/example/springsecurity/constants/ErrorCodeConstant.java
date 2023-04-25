package com.example.springsecurity.constants;

public class ErrorCodeConstant {

    /**
     * 系统内部异常
     */
    public static final String INTERNAL_SERVER_ERROR = "0001";

    /**
     * 请求参数错误
     */
    public static final String BAD_REQUEST = "0002";

    /**
     * 请求错误
     */
    public static final String BUSINESS_EXCEPTION = "0003";

    /**
     * token失效
     */
    public static final String INVALID_TOKEN = "0004";
}
