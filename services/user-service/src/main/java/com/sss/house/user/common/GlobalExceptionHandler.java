package com.sss.house.user.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobalExceptionHandler class
 *  异常处理类
 * @author Sss
 * @date 2018/12/15
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 异常处理方法，在status为200时进行调用，目的是为了将异常类转换为相应的rest响应
     * @param request
     * @param throwable
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public RestResponse<Object> handleException(HttpServletRequest request,Throwable throwable){
        LOGGER.error(throwable.getMessage(),throwable);
        RestCode restCode = ExceptionToCodeRepo.getCode(throwable);
        RestResponse<Object> restResponse = new RestResponse<>(restCode.code,restCode.msg);
        return restResponse;
    }

}
