package com.artisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/3 20:40
 * @mark: show me the code , change the world
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignOrderApplication.class, args);
    }

}
    