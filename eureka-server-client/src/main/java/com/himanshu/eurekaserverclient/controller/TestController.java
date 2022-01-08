package com.himanshu.eurekaserverclient.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private EurekaClient discoveryClient;

    @GetMapping("/get-hello")
    public String hello() {
        Application clientApplication = discoveryClient.getApplication("EUREKASERVERCLIENT");
        System.out.println(clientApplication.getInstances().get(0).getInstanceId());
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("EUREKASERVERCLIENT", false);
        return "Hello from eureka client with instance id " + instanceInfo.getInstanceId();
    }
}
