package com.example.springsecurity.handler;

import com.example.springsecurity.common.constants.ErrorCodeConstant;
import com.example.springsecurity.common.entity.BaseResponseEntity;
import com.example.springsecurity.common.enums.BusinessErrorCodes;
import com.example.springsecurity.common.exception.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spring.web.plugins.Docket;

@Slf4j
@RestControllerAdvice
public class GlobalApiHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class converterType) {
        return !returnType.getGenericParameterType().equals(BaseResponseEntity.class) &&
                !returnType.getDeclaringClass().equals(Docket.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType mediaType,
                                  @NonNull Class selectedConverterType, ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (request.getURI().getRawPath().contains("swagger") || request.getURI().getRawPath().contains("api-docs")) {
            return body;
        } else {
            if (body instanceof BaseResponseEntity) {
                return body;
            }
            String code = ErrorCodeConstant.SUCCESS_CODE;
            String message = BusinessErrorCodes.SUCCESS.getMessage();
            // String类型不能直接包装，所以要进行些特别的处理
            if (returnType.getGenericParameterType().equals(String.class)) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // 将数据包装在Result里后，再转换为json字符串响应给前端
                    return objectMapper.writeValueAsString(new BaseResponseEntity<>(code, message, body));
                } catch (JsonProcessingException e) {
                    throw new BusinessException(BusinessErrorCodes.DEFAULT_BUSINESS_ERROR);
                }
            }

            return new BaseResponseEntity<>(code, message, body);
        }
    }

    /**
     * 全局异常
     *
     * @return 系统错误
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        String errorCode = ErrorCodeConstant.INTERNAL_SERVER_ERROR;
        String message = BusinessErrorCodes.DEFAULT_BUSINESS_ERROR.getMessage();
        BaseResponseEntity<Object> baseResultVO = new BaseResponseEntity<>(errorCode, message, null);
        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(baseResultVO);
    }

    /**
     * 用户自定义异常
     *
     * @param e 异常
     * @return 数据
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessException(BusinessException e) {
        String code = ErrorCodeConstant.INTERNAL_SERVER_ERROR;
        String message = e.getMessage();
        BaseResponseEntity<Object> baseResultVO = new BaseResponseEntity<>(code, message, null);
        return ResponseEntity.status(HttpStatus.OK).body(baseResultVO);
    }
}