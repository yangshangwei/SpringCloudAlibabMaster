package com.artisan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
@Slf4j
public class OrderInfoController {


    @Value("${isNewPath}")
    private Boolean isNewPath;


    @RequestMapping("/selectOrderInfoById/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo) {

        log.info("是否业务走新逻辑:{}", isNewPath);
        if (isNewPath) {
            return "查询订单执行新逻辑->execute new logic : " + orderNo;
        }
        return "查询订单执行老逻辑->execute old logic : " + orderNo;

    }

}
