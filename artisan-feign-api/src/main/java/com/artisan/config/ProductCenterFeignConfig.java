package com.artisan.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author 小工匠
 * @version 1.0
 * @description:   千万不要添加@Configuration,不然会被作为全局配置文件共享
 * @date 2022/2/3 22:19
 * @mark: show me the code , change the world
 */


public class ProductCenterFeignConfig {


    @Bean
    public Logger.Level level(){
        return Logger.Level.BASIC;
    }
}
    