package com.example.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponseEntity<T> {

    /**
     * 调用成功返回code
     */
    public static final String SUCCESS_CODE = "0000";

    /**
     * 调用成功返回信息
     */
    public static final String SUCCESS_MSG = "success";

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("code")
    private String code;

    private String message;

    private T data;

    public static <T> BaseResponseEntity<T> SUCCESS(T data) {
        BaseResponseEntity<T> baseResponse = new BaseResponseEntity<>();
        baseResponse.setMessage(SUCCESS_MSG);
        baseResponse.setData(data);
        baseResponse.setCode(SUCCESS_CODE);
        return baseResponse;
    }

}
