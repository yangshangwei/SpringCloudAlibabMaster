package com.artisan.controller;

import com.artisan.common.entity.OrderInfo;
import com.artisan.common.entity.PayInfo;
import com.artisan.common.entity.ProductInfo;
import com.artisan.common.vo.OrderAndPayVo;
import com.artisan.common.vo.OrderVo;
import com.artisan.feignapi.product.ProductCenterFeignAPI;
import com.artisan.mapper.OrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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





}
    