package com.artisan.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.druid")
@Data
public class DruidDataSourceProperties {

    private String username;

    private String password;

    private String jdbcUrl;

    private String driverClassName;

    private Integer initialSize;

    private Integer maxActive;

    private Integer minIdle;

    private long maxWait;

    private boolean poolPreparedStatements;

}
