package com.artisan.controller;

import com.artisan.common.entity.OrderInfo;
import com.artisan.common.entity.PayInfo;
import com.artisan.common.entity.ProductInfo;
import com.artisan.common.vo.OrderAndPayVo;
import com.artisan.common.vo.OrderVo;
import com.artisan.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/2 03:20
 * @mark: show me the code , change the world
 */
@RestController
@Slf4j
public class OrderInfoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    /**
     * 调用地址， 注册中心 地址
     */
    public static final String PRODUCT_URI = "http://artisan-product-center/selectProductInfoById/";
    public static final String PAY_URI = "http://artisan-pay-center/selectPayInfoByOrderNo/";


    @RequestMapping("/v2/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo) {

        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }

        // 发起远程Http调用
        ResponseEntity<ProductInfo> responseEntity = restTemplate.getForEntity(PRODUCT_URI + orderInfo.getProductNo(), ProductInfo.class);

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


    @RequestMapping("/getOrderAndPayInfoByOrderNo/{orderNo}")
    public Object getOrderAndPayInfoByOrderNo(@PathVariable("orderNo") String orderNo) {
        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }

        ResponseEntity<PayInfo> responseEntity = restTemplate.getForEntity(PAY_URI + orderInfo.getProductNo(), PayInfo.class);
        PayInfo payInfo = responseEntity.getBody();
        if (payInfo == null) {
            return "没有对应的支付信息";
        }

        OrderAndPayVo orderAndPayVo = new OrderAndPayVo();
        orderAndPayVo.setOrderNo(orderNo);
        orderAndPayVo.setProductNo(orderInfo.getProductNo());
        orderAndPayVo.setProductCount(orderInfo.getProductCount());
        orderAndPayVo.setPayNo(payInfo.getPayNo());
        orderAndPayVo.setPayTime(payInfo.getPayTime());
        orderAndPayVo.setUserName(orderInfo.getUserName());
        return orderAndPayVo;
    }


}
    