package com.sss.house.api.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

/**
 * NewRuleConfig class
 *
 * @author Sss
 * @date 2018/12/15
 */
public class NewRuleConfig {

    @Autowired
    private IClientConfig clientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig clientConfig){
        //发送健康监测请求的url
        return new PingUrl(false,"/health");
    }

    @Bean
    public IRule ribbonRule(IClientConfig config){
        //可用的服务方返回
        return new AvailabilityFilteringRule();
    }

}
