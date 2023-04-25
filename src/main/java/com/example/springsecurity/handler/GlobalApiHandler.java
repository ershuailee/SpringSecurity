package com.example.springsecurity.handler;


import com.example.springsecurity.constants.ErrorCodeConstant;
import com.example.springsecurity.entity.ApiResult;
import com.example.springsecurity.entity.BaseResponseEntity;
import com.example.springsecurity.enums.BusinessErrorCodes;
import com.example.springsecurity.exception.BusinessException;
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
        return !returnType.getGenericParameterType().equals(ApiResult.class) &&
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
            return BaseResponseEntity.SUCCESS(body);
        }
    }

    /**
     * 全局异常
     *
     * @return 系统错误
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        e.printStackTrace();
        BaseResponseEntity<Object> baseResultVO = new BaseResponseEntity<>();
        baseResultVO.setCode(ErrorCodeConstant.INTERNAL_SERVER_ERROR);
        baseResultVO.setMessage(BusinessErrorCodes.DEFAULT_BUSINESS_ERROR.getMessage());
        log.error(e.getMessage());
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
        BaseResponseEntity<Object> baseResultVO = new BaseResponseEntity<>();
        baseResultVO.setCode(ErrorCodeConstant.INTERNAL_SERVER_ERROR);
        baseResultVO.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(baseResultVO);
    }
}