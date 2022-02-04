package com.artisan.controller;

import com.artisan.config.CustomConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/4 21:25
 * @mark: show me the code , change the world
 */

@RestController
@EnableConfigurationProperties(value = CustomConfig.class)
@Slf4j
public class CustomConfigController {

    @Autowired
    private CustomConfig customConfig;

    @RequestMapping("/testCustomProperties")
    public String test(){
        Map<String, Object> domainNumMap = customConfig.getDomainNumMap();

        log.info(domainNumMap.toString());

        return domainNumMap.toString();
    }
}
    