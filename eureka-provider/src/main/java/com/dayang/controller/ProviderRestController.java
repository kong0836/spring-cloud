package com.dayang.controller;

import com.google.gson.Gson;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import entity.HouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author konglinghui
 * @description
 * @date 2021/4/9 11:42
 */
@RestController
@RequestMapping("/provider")
public class ProviderRestController {

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

        return new Gson().toJson(instanceInfo1);
    }

    @GetMapping("/house/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        return new HouseInfo(1L, "上海", "虹口", "东体小区");
    }

    @GetMapping("/house/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return name;
    }
}
