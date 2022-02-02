package com.artisan.controller;

import com.artisan.common.entity.PayInfo;
import com.artisan.mapper.PayInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
public class PayInfoController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private PayInfoMapper payInfoMapper;


    @RequestMapping("/selectPayInfoByOrderNo/{orderNo}")
    public PayInfo selectPayInfoByOrderNo(@PathVariable("orderNo") String orderNo) {
        log.info("PayInfoController {} 被请求了",port);
        return payInfoMapper.selectPayInfoByOrderNo(orderNo);
    }

    @RequestMapping("/save")
    public String savePayInfo(@RequestBody PayInfo payInfo) {
        log.info("PayInfoController {} 被请求了",port);
        log.info("payInfo:{}", payInfo);
        return payInfo.getOrderNo();
    }

}
    