package com.example.springsecurity.entity;

import lombok.Data;

@Data
public class ApiResult<T> {

    private String code;
    private String message;
    private T data;

    public ApiResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
