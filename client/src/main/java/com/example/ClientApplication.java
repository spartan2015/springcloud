package com.example;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.List;


/**
 * needs services from eureka registry but is itself not a discoverable service (though it could)
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController

public class ClientApplication {

    @Inject
    private EurekaClient eurekaClient;

    @Inject
    private DiscoveryClient discoveryClient;

    @Inject
    private RestTemplateBuilder restTemplateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @RequestMapping("/")
    public String work(){
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(getBaseUrlUsingEurekaClient(), HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    private RestTemplate getRestTemplate() {
        return restTemplateBuilder.build();
    }

    private String getBaseUrlUsingDiscoveryClient() {
        List<ServiceInstance> serviceList = discoveryClient.getInstances("SERVICE");
        return  serviceList.get(0).getUri().toString();
    }

    private String getBaseUrlUsingEurekaClient() {
        // supports client side load balancing
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("SERVICE",false);
        return  instanceInfo.getHomePageUrl();
    }
}
