package com.example;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureTheRibbon {

    @Bean
    public IRule ribbonRule(){
        /**
         * RandomRule         *
         */
        return new RoundRobinRule();
    }

    @Bean
    public IPing r(){
        /*
        DummyPing
        PingUrl
        NIWSDiscoveryPing
        etc
         */
        return new PingUrl();
    }
}
