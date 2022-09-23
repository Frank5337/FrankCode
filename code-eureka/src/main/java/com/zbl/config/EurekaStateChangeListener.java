package com.zbl.config;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: Created in 9:19 2020/3/17
 * @Description:
 * @Version: $
 *
 * EurekaServerStartedEvent - Eureka服务端启动事件
 * EurekaRegistryAvailableEvent - Eureka服务端可用事件
 * EurekaInstanceRegisteredEvent - Eureka客户端服务注册事件
 * EurekaInstanceRenewedEvent - Eureka客户端续约事件
 * EurekaInstanceCanceledEvent - Eureka客户端下线事件
 *
 * 作者：相见无望zxc
 * 链接：https://www.jianshu.com/p/1e89e2969529
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
@Component
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        //服务断线事件
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String serverId = eurekaInstanceCanceledEvent.getServerId();
        System.out.println(appName);
        System.out.println(serverId);

        System.out.println("EurekaInstanceCanceledEvent");

    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.out.println(instanceInfo);
        System.out.println("有服务注册进来了: " + instanceInfo.getAppName());
        System.out.println("EurekaInstanceRegisteredEvent");

    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        event.getAppName();
        event.getServerId();
        System.out.println("EurekaInstanceRenewedEvent, \nAppName: " + event.getAppName() +  "\nServerId: " +event.getServerId());


    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.out.println("EurekaRegistryAvailableEvent");

    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {

        System.out.println("EurekaServerStartedEvent");
        //Server启动
    }
}
