package com.example.springsecurity.common.entity;

import lombok.Data;

@Data
public class BaseResponseEntity<T> {

    private String code;
    private String message;
    private T data;

    public BaseResponseEntity(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
