package com.sss.house.user.common;

import com.google.common.collect.ImmutableMap;
import com.sss.house.user.exception.IllegalParamsException;
import com.sss.house.user.exception.WithTypeException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.reflect.FieldUtils;

/**
 * ExceptionToCodeRepo class
 *  目的使将异常处理为已经建立的restCode映射
 * @author Sss
 * @date 2018/12/15
 */
class ExceptionToCodeRepo {

    /**
     * 建立错误异常与RestCode之间的映射
     */
    private static final ImmutableMap<Object,RestCode> map = ImmutableMap.<Object, RestCode>builder()
            .put(IllegalParamsException.Type.WRONG_PAGE_NUM,RestCode.WRONG_PAGE)
            .put(IllegalStateException.class,RestCode.UNKNOWN_ERROR)
            .build();

    private static Object getType(Throwable throwable){
        try{
            //反射获取异常类的属性，其中type为设置的枚举类
            return FieldUtils.readDeclaredField(throwable,"type",true);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    static RestCode getCode(Throwable throwable){
        if(throwable == null){
            return RestCode.UNKNOWN_ERROR;
        }

        Object target = throwable;
        if(target instanceof WithTypeException){
            Object type = getType(throwable);
            if(type != null){
                target = type;
            }
        }


        //根据type在map中获取相应的restCode
        RestCode restCode = map.get(target);
        if(restCode != null){
            return restCode;
        }

        //若此时得到的restCode为空则说明还没有或得到根异常类
        Throwable rootCause = ExceptionUtils.getRootCause(throwable);
        if(rootCause != null){
            return getCode(rootCause);
        }

        return RestCode.UNKNOWN_ERROR;

    }
}
