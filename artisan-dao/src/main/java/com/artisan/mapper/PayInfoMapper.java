package com.artisan.mapper;


import com.artisan.common.entity.PayInfo;

public interface PayInfoMapper {

    PayInfo selectPayInfoByOrderNo(String orderNo);
}
