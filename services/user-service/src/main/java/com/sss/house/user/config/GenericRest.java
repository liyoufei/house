package com.sss.house.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * GenericRest class
 *
 * @author Sss
 * @date 2018/12/14
 */
@Service
public class GenericRest {

    /**
     * 自动装载，默认和Bean方法名相同
     */
    @Autowired
    private RestTemplate ldRestTemplate;

    @Autowired
    private RestTemplate directRestTemplate;

    private static final String DIRECT_FLAG = "direct://";

    /**
     * 模拟post方法
     * @param url 请求地址
     * @param reqBody 请求体
     * @param responseType 返回类型，因为简单的泛型不能得到用于反射的class，所以这里要用参数化的泛型来进行后续的反射操作
     * @param <T> 泛型
     * @return 响应实体
     */
    public <T> ResponseEntity<T> post(String url, Object reqBody, ParameterizedTypeReference<T> responseType){
        RestTemplate restTemplate = getRestTemplate(url);
        url = url.replace(DIRECT_FLAG,"");
        return restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<Object>(reqBody),responseType);

    }


    /**
     * 模拟post方法
     * @param url 请求地址
     * @param responseType 返回类型，因为简单的泛型不能得到用于反射的class，所以这里要用参数化的泛型来进行后续的反射操作
     * @param <T> 泛型
     * @return 响应实体
     */
    public <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType){
        RestTemplate restTemplate = getRestTemplate(url);
        url = url.replace(DIRECT_FLAG,"");
        //此处虽然没有请求体但是仍然要传进去空的HTTPEntity
        return restTemplate.exchange(url,HttpMethod.GET,HttpEntity.EMPTY,responseType);

    }

    /**
     * 根据是否含有direct来选择不同的调用方法
     * @param url 请求地址
     * @return rest模板
     */
    private RestTemplate getRestTemplate(String url){
        if(url.contains(DIRECT_FLAG)){
            return directRestTemplate;
        }else{
            return ldRestTemplate;
        }
    }

}
