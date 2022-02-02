package com.artisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ArtisanRibbonOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtisanRibbonOrderApplication.class, args);
	}

}
