package com.dayang.client;

import com.dayang.config.FeignConfiguration;
import entity.HouseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author konglinghui
 * @description
 * @date 2021/5/6 17:20
 */
@FeignClient(value = "eureka-provider",configuration = FeignConfiguration.class)
public interface ProviderFeignClient {

    @GetMapping("/provider/metadata")
    Object metadata();

    @GetMapping("/provider/house/data")
    HouseInfo getData(@RequestParam("name") String name);

    @GetMapping("/provider/house/data/{name}")
    String getData2(@PathVariable("name") String name);
}
