package com.artisan.controller;

import com.artisan.common.entity.ProductInfo;
import com.artisan.mapper.ProductInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/3 21:42
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
    public ProductInfo selectProductInfoById(@PathVariable("productNo") String productNo) throws InterruptedException {
        log.info("artisan-cloud-feign-product - {} 被调用了", port);
        ProductInfo productInfo = productInfoMapper.selectProductInfoById(productNo);
        return productInfo;
    }


    @RequestMapping("/getToken4Header")
    public String getToken4Header(@RequestHeader("token") String token){
        log.info("ProductInfoController - token:{}",token);
        return token;
    }

}
    