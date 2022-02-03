package com.artisan.customrules;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;

import java.util.List;

/**
 * @author 小工匠
 * @version 1.0
 * @description: 根据权重选择随机选择一个
 * @date 2022/2/3 0:28
 * @mark: show me the code , change the world
 */

public class ArtisanWeightedBalancer extends Balancer {

    public static Instance chooseInstanceByRandomWeight(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}
