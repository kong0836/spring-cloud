package com.dayang.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/hello")
    public String hello() {
//        return restTemplate.getForObject("http://127.0.0.1:8081/demo/hello", String.class);
        return restTemplate.getForObject("http://eureka-provider/demo/hello", String.class);
    }
}
