package com.sss.house.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * HttpClientProperties class
 *
 * @author Sss
 * @date 2018/12/14
 */

@ConfigurationProperties(prefix = "spring.httpclient")
public class HttpClientProperties {
    private int connTimeOut = 1000;
    private int socketTimeOut = 10000;
    private String agent = "agent";
    private int maxConnPerRoute = 10;
    private int macConnTotal = 50;

    public int getConnTimeOut() {
        return connTimeOut;
    }

    public void setConnTimeOut(int connTimeOut) {
        this.connTimeOut = connTimeOut;
    }

    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public int getMaxConnPerRoute() {
        return maxConnPerRoute;
    }

    public void setMaxConnPerRoute(int maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
    }

    public int getMacConnTotal() {
        return macConnTotal;
    }

    public void setMacConnTotal(int macConnTotal) {
        this.macConnTotal = macConnTotal;
    }
}
