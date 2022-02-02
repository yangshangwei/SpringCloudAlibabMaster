package com.globalconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/2 19:45
 * @mark: show me the code , change the world
 */
@Configuration
public class ProductCenterRibbonConfig {

    @Bean
    public IRule randomRule() {
        return new RandomRule();
    }
}
