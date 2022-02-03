package com.artisan.feignapi.product;

import com.artisan.common.entity.ProductInfo;
import com.artisan.config.ProductCenterFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "artisan-product-center" , configuration = ProductCenterFeignConfig.class)
public interface ProductCenterFeignAPI {


    @RequestMapping("/selectProductInfoById/{productNo}")
    ProductInfo selectProductInfoById(@PathVariable("productNo") String productNo);
}
