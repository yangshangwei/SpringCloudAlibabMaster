package com.artisan.feignapi.pay;

import com.artisan.common.entity.PayInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "artisan-pay-center")
public interface PayCenterFeignAPI {

    @RequestMapping("/selectPayInfoByOrderNo/{orderNo}")
    PayInfo selectPayInfoByOrderNo(@PathVariable("orderNo") String orderNo);
}
