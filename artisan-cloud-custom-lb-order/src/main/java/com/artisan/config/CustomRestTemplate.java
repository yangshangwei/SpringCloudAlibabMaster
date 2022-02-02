package com.artisan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

/**
 * @author 小工匠
 * @version 1.0
 * @description: 根据RestTemplate特性自己改造
 * @date 2022/2/2 13:32
 * @mark: show me the code , change the world
 */

@Slf4j
public class CustomRestTemplate extends RestTemplate {

    @Autowired
    private DiscoveryClient discoveryClient;

    public CustomRestTemplate(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        Assert.notNull(url, "URI is required");
        Assert.notNull(method, "HttpMethod is required");
        ClientHttpResponse response = null;
        try {

            /**
             * 在这里拦截一下子 偷梁换柱
             */

            //把服务名 替换成我们的IP
            url = replaceUrl(url);
            log.info("替换后的请求路径:{}", url);

            ClientHttpRequest request = createRequest(url, method);
            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }
            response = request.execute();
            handleResponse(url, method, response);
            return (responseExtractor != null ? responseExtractor.extractData(response) : null);
        } catch (IOException ex) {
            String resource = url.toString();
            String query = url.getRawQuery();
            resource = (query != null ? resource.substring(0, resource.indexOf('?')) : resource);
            throw new ResourceAccessException("I/O error on " + method.name() +
                    " request for \"" + resource + "\": " + ex.getMessage(), ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    /**
     * 方法实现说明:把微服务名称  去注册中心拉取对应IP进行调用
     * http://artisan-product-center/selectProductInfoById/1
     *
     * @param url:请求的url
     * @return:
     * @exception:
     */
    private URI replaceUrl(URI url) {
        log.info("原始请求路径为:{}", url);

        //1:从URI中解析调用的调用的serviceName=artisan-product-center
        String serviceName = url.getHost();
        log.info("调用微服务的名称:{}", serviceName);

        //2:解析我们的请求路径 reqPath= /selectProductInfoById/1
        String reqPath = url.getPath();
        log.info("请求path:{}", reqPath);


        //通过微服务的名称去nacos服务端获取 对应的实例列表
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
        if (serviceInstanceList.isEmpty()) {
            throw new RuntimeException("没有可用的微服务实例列表:" + serviceName);
        }

        String serviceIp = chooseTargetIp(serviceInstanceList);

        String source = serviceIp + reqPath;
        try {
            return new URI(source);
        } catch (URISyntaxException e) {
            log.error("根据source:{}构建URI异常", source);
        }
        return url;
    }

    /**
     * 方法实现说明:从服务列表中 随机选举一个ip
     *
     * @param serviceInstanceList 服务列表
     * @return: 调用的ip
     * @exception:
     */
    private String chooseTargetIp(List<ServiceInstance> serviceInstanceList) {
        //采取随机的获取一个
        Random random = new Random();
        Integer randomIndex = random.nextInt(serviceInstanceList.size());
        String serviceIp = serviceInstanceList.get(randomIndex).getUri().toString();
        log.info("随机选举的服务IP:{}", serviceIp);
        return serviceIp;
    }
}
    