package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;


/**
 * needs services from eureka registry but is itself not a discoverable service (though it could)
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ConfigClientApplication {

    @Inject
    private ConfigClientProperties properties;

    @Value("${some.property}")
    private String someProperty;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @RequestMapping("/")
    public String worker(){
        return "hello: " + someProperty;
    }
}
