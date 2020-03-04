package com.zbl.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: zbl
 * @Date: Created in 17:27 2020/3/3
 * @Description:
 * @Version: $
 */

@RestControllerAdvice
public class GlobalException {

    /**
     * 业务异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.OK)
    public String businessException(AuthenticationException e) {
        return "认证方式错误";

    }
}
