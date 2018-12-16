package com.sss.house.user.common;

/**
 * RestResponse class
 *
 * @author Sss
 * @date 2018/12/14
 */
public class RestResponse<T> {
    private int code;
    private String msg;
    private T result;

    /**
     * 成功的默认返回
     * @return 模板
     */
    public static  RestResponse success(){
        return new RestResponse();
    }

    /**
     * 默认的成功类型，并设置返回结果
     * @param result 返回结果
     * @param <T> 结果类型
     * @return 模板
     */
    public static <T> RestResponse<T> success(T result){
        RestResponse<T> restResponse = new RestResponse<T>();
        restResponse.setResult(result);
        return restResponse;

    }

    /**
     * 错误的话直接返回错误码
     * @param restCode 错误码
     * @return 模板
     */
    public static RestResponse error(RestCode restCode){
        return new RestResponse(restCode.code,restCode.msg);
    }

    private RestResponse() {
        this(RestCode.OK.code,RestCode.OK.msg);
    }

    public RestResponse(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
