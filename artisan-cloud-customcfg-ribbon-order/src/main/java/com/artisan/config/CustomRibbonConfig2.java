package com.artisan.config;

import com.globalconfig.GlobalRibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小工匠
 * @version 1.0
 * @description: Ribbon 全局配置，通过代码实现
 * @date 2022/2/3 0:05
 * @mark: show me the code , change the world
 */


@Configuration
@RibbonClients(defaultConfiguration = GlobalRibbonConfig.class)
public class CustomRibbonConfig2 {
}
    