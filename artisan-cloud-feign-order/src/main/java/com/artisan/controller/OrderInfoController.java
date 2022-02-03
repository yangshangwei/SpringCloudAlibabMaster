package com.artisan.controller;

import com.artisan.common.entity.OrderInfo;
import com.artisan.common.entity.PayInfo;
import com.artisan.common.entity.ProductInfo;
import com.artisan.common.vo.OrderAndPayVo;
import com.artisan.common.vo.OrderVo;
import com.artisan.feignapi.pay.PayCenterFeignAPI;
import com.artisan.feignapi.product.ProductCenterFeignAPI;
import com.artisan.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ProductCenterFeignAPI productCenterFeignAPI;

    @Autowired
    private PayCenterFeignAPI payCenterFeignAPI;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @RequestMapping("/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo) {

        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }

        // feign调用
        ProductInfo productInfo =  productCenterFeignAPI.selectProductInfoById(orderInfo.getProductNo());

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

        PayInfo payInfo = payCenterFeignAPI.selectPayInfoByOrderNo(orderNo);
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


    @RequestMapping("/testTokenDelivery")
    public String testTokenDelivery(@RequestHeader("token")  String token){
        String token4Header = productCenterFeignAPI.getToken4Header(token);
        log.info("要传递的Token {}", token);
        return "success " + token4Header;
    }
}
    