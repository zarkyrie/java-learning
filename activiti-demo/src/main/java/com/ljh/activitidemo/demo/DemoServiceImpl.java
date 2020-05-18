package com.ljh.activitidemo.demo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public void test() {
        System.out.println("hello");
    }
}
