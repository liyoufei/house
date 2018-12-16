package com.sss.house.user.exception;

/**
 * IllegalParamsException class
 *
 * @author Sss
 * @date 2018/12/15
 */
public class IllegalParamsException extends RuntimeException implements WithTypeException {


    private Type type;

    public IllegalParamsException(Type type,String msg){

        super(msg);
        this.type = type;
    }

    public Type type(){
        return type;
    }

    public enum Type{
        /**
         * 错误的页码
         */
        WRONG_PAGE_NUM,
        /**
         * 错误的类型
         */
        WRONG_TYPE
    }
}
