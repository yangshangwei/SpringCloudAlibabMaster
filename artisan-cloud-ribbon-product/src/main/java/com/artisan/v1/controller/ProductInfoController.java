package com.artisan.v1.controller;

import com.artisan.common.entity.ProductInfo;
import com.artisan.mapper.ProductInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/1 21:30
 * @mark: show me the code , change the world
 */

@RestController
@Slf4j
public class ProductInfoController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @RequestMapping("/selectProductInfoById/{productNo}")
    public Object selectProductInfoById(@PathVariable("productNo") String productNo) {
        log.info("{} 被请求了",port);
        ProductInfo productInfo = productInfoMapper.selectProductInfoById(productNo);
        return productInfo;
    }

}
    