package com.zbl.code.common.base;

import com.zbl.code.common.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: zbl
 * @Date: Created in 11:01 2019/8/21
 * @Description: 全局异常拦截器
 * @Version: $
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 业务异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult businessException(BaseException e) {
        return JsonResult.failure(e.getCode(), e.getMsg());
    }

    /**
     * 全局异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult exception(Exception e) {
        log.error("全局异常信息 = {}", e.getMessage(), e);
        return JsonResult.failure(e.getMessage());
    }

}
