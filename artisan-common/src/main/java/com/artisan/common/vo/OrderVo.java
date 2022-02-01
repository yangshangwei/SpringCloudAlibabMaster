package com.artisan.common.vo;

import lombok.Data;


@Data
public class OrderVo {

    private String orderNo;

    private String userName;

    private String productName;

    private Integer productNum;
}
