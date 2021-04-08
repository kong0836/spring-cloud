package com.dayang.mis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author konglinghui
 * @description eureka集群搭建
 * @date 2021/4/8 16:59
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerClusterApplication.class, args);
    }
}
