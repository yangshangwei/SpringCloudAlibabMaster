package com.artisan.mapper;


import com.artisan.common.entity.OrderInfo;

public interface OrderInfoMapper {

    OrderInfo selectOrderInfoById(String orderNo);
}
