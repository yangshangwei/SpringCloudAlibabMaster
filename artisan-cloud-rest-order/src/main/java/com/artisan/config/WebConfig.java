package com.artisan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/1 21:18
 * @mark: show me the code , change the world
 */

@Configuration
public class WebConfig {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
    