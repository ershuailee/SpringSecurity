package com.example.springsecurity.common.exception;

import com.example.springsecurity.common.enums.BusinessErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String errorCode;

    private String message;

    public BusinessException(BusinessErrorCodes businessErrorCodes) {
        this.errorCode = businessErrorCodes.getCode();
        this.message = businessErrorCodes.getMessage();
    }
}
