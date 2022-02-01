package com.artisan.v1.controller;

import com.artisan.common.entity.OrderInfo;
import com.artisan.common.entity.ProductInfo;
import com.artisan.common.vo.OrderVo;
import com.artisan.mapper.OrderInfoMapper;
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
 * @date 2022/2/1 21:20
 * @mark: show me the code , change the world
 */
@RestController
public class OrderInfoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    /**
     * 调用地址，硬编码
     */
    public static final String uri = "http://localhost:9999/selectProductInfoById/";

    @RequestMapping("/selectOrderInfoById/{orderNo}")
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
    