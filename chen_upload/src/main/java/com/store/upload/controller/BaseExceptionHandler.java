package com.store.upload.controller;

import entity.Result;
import entity.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error("捕获异常 {}", e);
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
