package com.dayang.controller;

import com.dayang.client.ProviderFeignClient;
import entity.HouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author konglinghui
 * @description
 * @date 2021/4/8 16:24
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private ProviderFeignClient providerFeignClient;

    @GetMapping("/hello")
    public String hello() {
//        return restTemplate.getForObject("http://127.0.0.1:8081/demo/hello", String.class);
        return restTemplate.getForObject("http://eureka-provider/provider/hello", String.class);
    }

    @GetMapping("/call/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        return providerFeignClient.getData(name);
        // return restTemplate.getForObject("http://eureka-provider/provider/house/data?name=" + name, HouseInfo.class);
    }

    @GetMapping("/call/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return providerFeignClient.getData2(name);
        // return restTemplate.getForObject("http://eureka-provider/provider/house/data/{name}", String.class, name);
    }

    @GetMapping("/choose")
    public ServiceInstance chooseUrl() {
        ServiceInstance choose = loadBalancer.choose("eureka-consumer");
        return choose;
    }
}
