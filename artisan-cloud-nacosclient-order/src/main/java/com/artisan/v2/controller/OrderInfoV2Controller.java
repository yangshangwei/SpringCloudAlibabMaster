package com.artisan.v2.controller;

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

import java.net.URI;
import java.util.List;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/2 03:20
 * @mark: show me the code , change the world
 */
@RestController
@Slf4j
public class OrderInfoV2Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 调用地址，硬编码
     */
//    public static final String uri = "http://localhost:9999/selectProductInfoById/";
    @RequestMapping("/v2/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo) {

        OrderInfo orderInfo = orderInfoMapper.selectOrderInfoById(orderNo);
        if (null == orderInfo) {
            return "根据orderNo:" + orderNo + "查询没有该订单";
        }

        /**
         * 以下仅为演示，实际开发中并不会这样使用
         */
        // 从nacos server获取 product-info的地址
        List<ServiceInstance> instances = discoveryClient.getInstances("artisan-product-center");


        if (null == instances || instances.isEmpty()) {
            return "Prod微服务没有对应的实例可用";
        }

        // 从对应的服务实例中获取访问地址
        ServiceInstance serviceInstance = instances.get(0);
        String uri = serviceInstance.getUri().toString();
        log.info("从nacos server中获取的prod地址：", uri);

        // 发起远程Http调用
        ResponseEntity<ProductInfo> responseEntity = restTemplate.getForEntity(uri + "/selectProductInfoById/" + orderInfo.getProductNo(), ProductInfo.class);

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


    @GetMapping("/v2/getInstance")
    public List<ServiceInstance> getInstances(@RequestParam(required = true) String appName) {
        return discoveryClient.getInstances(appName);
    }


    @GetMapping("/v2/getServices")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
}
    