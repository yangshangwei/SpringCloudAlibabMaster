package com.artisan.common.vo;

import lombok.Data;

import java.util.Date;


@Data
public class OrderAndPayVo {

    private String orderNo;

    private String userName;

    private String productNo;

    private Integer productCount;

    private String payNo;

    private Date payTime;
}
