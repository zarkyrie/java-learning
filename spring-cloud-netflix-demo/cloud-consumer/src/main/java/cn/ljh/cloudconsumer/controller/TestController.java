package cn.ljh.cloudconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumerTest")
    public String consumerTest() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/providerTest1";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
