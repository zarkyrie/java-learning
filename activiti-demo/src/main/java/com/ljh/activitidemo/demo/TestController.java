package com.ljh.activitidemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private DemoService demoService;

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

    @GetMapping("/start")
    public String start() {
        testService.start();
        return "start";
    }

    @GetMapping("/query")
    public String query() {
        testService.query();
        return "start";
    }

    @GetMapping("/finish")
    public String finish() {
        testService.finish();
        return "start";
    }

    @GetMapping("/demo")
    public void demo(){
        demoService.test();
    }
}
