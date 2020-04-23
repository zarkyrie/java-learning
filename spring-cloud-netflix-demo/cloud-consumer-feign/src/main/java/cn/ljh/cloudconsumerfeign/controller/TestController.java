package cn.ljh.cloudconsumerfeign.controller;

import cn.ljh.cloudconsumerfeign.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/feignTest")
    public String feignTest() {
        return testService.test();
    }
}
