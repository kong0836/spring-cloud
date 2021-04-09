package com.dayang.mis.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author konglinghui
 * @description
 * @date 2021/4/9 11:42
 */
@RestController
@RequestMapping("/eureka")
public class EurekaRestController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取元数据
     *
     * @return
     */
    @GetMapping("/metadata")
    public Object metadata() {
        // eurekaClient方式
        List<InstanceInfo> instanceInfoList = eurekaClient.getInstancesByVipAddress("eureka-provider", false);
        InstanceInfo instanceInfo = instanceInfoList.get(0);

        // eurekaDiscoveryClient方式
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("eureka-provider");
        InstanceInfo instanceInfo1 = ((EurekaDiscoveryClient.EurekaServiceInstance) serviceInstanceList.get(0)).getInstanceInfo();

        return instanceInfo;
    }
}
