package cn.ljh.cloudconsumerribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumerTest")
    public String consumerTest() {
        return restTemplate.getForObject("http://provider/providerTest1", String.class);
    }
}
