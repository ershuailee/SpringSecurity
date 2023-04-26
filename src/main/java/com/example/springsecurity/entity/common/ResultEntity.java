package com.example.springsecurity.entity.common;

import lombok.Data;

@Data
public class ResultEntity<T> {

    private String code;
    private String message;
    private T data;

    public ResultEntity(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
