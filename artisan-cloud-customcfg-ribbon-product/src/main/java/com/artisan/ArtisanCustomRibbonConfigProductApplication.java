package com.artisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/2 19:40
 * @mark: show me the code , change the world
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ArtisanCustomRibbonConfigProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtisanCustomRibbonConfigProductApplication.class,args);
    }
}
    