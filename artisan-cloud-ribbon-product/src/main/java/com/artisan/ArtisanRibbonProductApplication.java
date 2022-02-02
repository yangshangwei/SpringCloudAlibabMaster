package com.artisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/2 0:55
 * @mark: show me the code , change the world
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ArtisanRibbonProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtisanRibbonProductApplication.class, args);
    }
}
    