package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * Created on 9/22/2017.
 */
@RestController
@EnableCircuitBreaker
public class MyLoadBalancingWithCircuitBreakerClient {

    @Inject
    private RestTemplate restTemplate;

    @GetMapping("/loadBalanced")
    @HystrixCommand(fallbackMethod = "fallback")
    public String work(){
        return restTemplate.getForEntity("http://service", String.class).getBody();
    }

    public String fallback(){
        return "fallback";
    }
}
