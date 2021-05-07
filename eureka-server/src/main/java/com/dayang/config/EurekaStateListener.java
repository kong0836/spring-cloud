package com.dayang.config;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * eureka服务上下线监控
 *
 * @author konglinghui
 * @date 2021/4/11 12:20
 **/
@Component
public class EurekaStateListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaStateListener.class);

    /**
     * 注册中心启动事件
     *
     * @param event
     */
    @EventListener
    public void listener(EurekaRegistryAvailableEvent event) {
        LOGGER.info("注册中心启动");
    }

    /**
     * Eureka Server启动事件
     *
     * @param event
     */
    @EventListener
    public void listener(EurekaServerStartedEvent event) {
        LOGGER.info("Eureka Server启动");
    }

    /**
     * 服务注册事件
     *
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        LOGGER.info("appName-{}服务进行注册", instanceInfo.getAppName());
    }

    /**
     * 服务下线事件
     *
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceCanceledEvent event) {
        LOGGER.info("serverId-{},appName-{}服务下线", event.getServerId(), event.getAppName());
    }

    /**
     * 服务续约事件
     *
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceRenewedEvent event) {
        LOGGER.info("serverId-{},appName-{}服务进行续约", event.getServerId(), event.getAppName());
    }
}
