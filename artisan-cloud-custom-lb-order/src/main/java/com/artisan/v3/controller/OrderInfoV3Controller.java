package com.artisan.v3.controller;

import com.artisan.common.entity.OrderInfo;
import com.artisan.common.entity.ProductInfo;
import com.artisan.common.vo.OrderVo;
import com.artisan.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 小工匠
 * @version 1.0
 * @description:
 * @date 2022/2/2 03:20
 * @mark: show me the code , change the world
 */
@RestController
@Slf4j
public class OrderInfoV3Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 调用地址， 注册中心 地址
     */
    public static final String uri = "http://artisan-product-center/selectProductInfoById/";

    @RequestMapping("/v3/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo) {

        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);

        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }


        // 发起远程Http调用
        ResponseEntity<ProductInfo> responseEntity = restTemplate.getForEntity(uri + orderInfo.getProductNo(), ProductInfo.class);

        ProductInfo productInfo = responseEntity.getBody();

        if (productInfo == null) {
            return "没有对应的商品";
        }

        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(orderInfo.getOrderNo());
        orderVo.setUserName(orderInfo.getUserName());
        orderVo.setProductName(productInfo.getProductName());
        orderVo.setProductNum(orderInfo.getProductCount());

        return orderVo;
    }

}
    