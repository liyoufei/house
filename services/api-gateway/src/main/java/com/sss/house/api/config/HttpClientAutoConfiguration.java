package com.sss.house.api.config;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.http.client.HttpClient;


/**
 * HttpClientAutoConfiguration class
 *
 * @author Sss
 * @date 2018/12/14
 */

@Configuration
@ConditionalOnClass(HttpClient.class)
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {

    private final HttpClientProperties properties;

    public HttpClientAutoConfiguration(HttpClientProperties httpClientProperties){
        this.properties = httpClientProperties;
    }

    /**
     * 用于拦截request。response打印输出
     */
    @Autowired
    private HttpRequestInterceptor httpRequestInterceptor;

    @Autowired
    private HttpResponseInterceptor httpResponseInterceptor;


    @Bean
    @ConditionalOnMissingBean(HttpClient.class)
    public HttpClient httpClient(){
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(properties.getConnTimeOut())
                .setSocketTimeout(properties.getSocketTimeOut()).build();
        HttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setUserAgent(properties.getAgent())
                .setMaxConnPerRoute(properties.getMaxConnPerRoute())
                .setMaxConnTotal(properties.getMacConnTotal())
                .addInterceptorFirst(httpRequestInterceptor).addInterceptorFirst(httpResponseInterceptor)
                .build();
        return httpClient;
    }
}
