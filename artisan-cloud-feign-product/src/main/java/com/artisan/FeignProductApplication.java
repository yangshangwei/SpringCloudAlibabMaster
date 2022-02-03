package com.artisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/3 21:39
 * @mark: show me the code , change the world
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FeignProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProductApplication.class, args);

    }
}
    