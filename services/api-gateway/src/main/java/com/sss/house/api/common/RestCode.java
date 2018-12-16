package com.sss.house.api.common;

public enum RestCode {
    /**
     * 请求正常
     */
    OK(0,"OK"),
    /**
     * 服务异常
     */
    UNKOWN_ERROR(1,"服务异常"),
    /**
     * 页面不存在
     */
    WRONG_PAGE(2,"页面不存在");

    public int code;
    public String msg;

    RestCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
