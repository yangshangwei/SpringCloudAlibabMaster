package com.artisan.mapper;


import com.artisan.common.entity.ProductInfo;

public interface ProductInfoMapper {

    ProductInfo selectProductInfoById(String productNo);
}
