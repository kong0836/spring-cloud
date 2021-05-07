package com.dayang.config;

import com.dayang.interceptor.FeignBasicAuthRequestInterceptor;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author konglinghui
 * @description
 * @date 2021/5/7 11:41
 */
@Configuration
public class FeignConfiguration {

    /**
     * 日志级别
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Basic认证
     *
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

    /**
     * 配置自定义认证拦截器
     */
    // @Bean
    // public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor2() {
    //     return new FeignBasicAuthRequestInterceptor();
    // }
}
