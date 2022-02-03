package com.globalconfig;

import com.artisan.customrules.ArtisanWeightedRule;
import com.artisan.customrules.SameClusterPriorityRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小工匠
 * @version 1.0
 * @description: 全局负载均衡策略
 * @date 2022/2/3 0:06
 * @mark: show me the code , change the world
 */

@Configuration
public class GlobalRibbonConfig {

    @Bean
    public IRule globalConfig() {
        // 根据权重的规则
        // return new ArtisanWeightedRule();

        // 同集群优先调用规则
        return new SameClusterPriorityRule();
    }
}
    