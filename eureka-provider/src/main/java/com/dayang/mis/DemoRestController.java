package com.dayang.mis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author konglinghui
 * @description
 * @date 2021/4/8 16:06
 */
@RestController
@RequestMapping("/demo")
public class DemoRestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello eureka";
    }
}
