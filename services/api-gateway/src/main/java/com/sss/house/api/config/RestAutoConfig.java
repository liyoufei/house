package com.sss.house.api.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.http.client.HttpClient;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * RestAutoConfig class
 *
 * @author Sss
 * @date 2018/12/14
 */

@Configuration
public class RestAutoConfig {

    @Configuration
    public static class RestTemplateConfig{

        /**
         *  loadBalanced 注解，spring会拦截restTemplate进行拦截，将其替换成ip:port的映射
         * @param httpClient Bean
         * @return restTemplate模板
         */
        @Bean
        @LoadBalanced
        RestTemplate ldRestTemplate(HttpClient httpClient){
            RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
            //将消息编码改为utf-8
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("utf-8")));
            //消息与json的转换
            restTemplate.getMessageConverters().add(1,new FastJsonHttpMessageConverterL());
            return restTemplate;
        }

        /**
         * 进行直连
         * @param httpClient
         * @return
         */
        @Bean
        RestTemplate directRestTemplate(HttpClient httpClient){
            RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("utf-8")));
            restTemplate.getMessageConverters().add(1,new FastJsonHttpMessageConverterL());
            return restTemplate;
        }


        /**
         * 因为默认的converter类支持将所有的mediaType转换为json，故继承修改为application/json
         */
        static class FastJsonHttpMessageConverterL extends FastJsonHttpMessageConverter{

            static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

            private FastJsonHttpMessageConverterL (){
                setDefaultCharset(DEFAULT_CHARSET);
                setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,new MediaType("application","*+json")));

            }
        }
    }
}
