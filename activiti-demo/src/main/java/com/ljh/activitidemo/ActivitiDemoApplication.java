package com.ljh.activitidemo;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ActivitiDemoApplication implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ActivitiDemoApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public CommandLineRunner run1(){
        return args -> Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
