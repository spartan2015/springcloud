package com.example;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 9/22/2017.
 */
@Configuration
@RibbonClient(name="some-service" /*<ribbon_client_name>*/, configuration=ConfigureTheRibbon.class)
/*
then change application.properties

<ribbon_client_name>.ribbon.eureka.enabled=false
<ribbon_client_name>.ribbon.listOfServers=http://localhost:4444, http://localhost:5555

 */
public class MyLoadBalancingWithCircuitBreakerWithoutServiceDiscoveryConfiguration {

}
