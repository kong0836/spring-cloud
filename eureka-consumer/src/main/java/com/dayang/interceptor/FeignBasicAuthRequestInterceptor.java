package com.dayang.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author konglinghui
 * @description 自定义认证方式
 * @date 2021/5/7 12:00
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 业务逻辑
    }
}
