package com.artisan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/4 21:21
 * @mark: show me the code , change the world
 */

@ConfigurationProperties(prefix = "domain")
public class CustomConfig {

    private Map<String,Object> domainNumMap;

    public Map<String, Object> getDomainNumMap() {
        return domainNumMap;
    }

    public void setDomainNumMap(Map<String, Object> domainNumMap) {
        this.domainNumMap = domainNumMap;
    }
}
    