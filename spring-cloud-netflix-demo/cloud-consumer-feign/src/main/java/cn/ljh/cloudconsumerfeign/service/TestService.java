package cn.ljh.cloudconsumerfeign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("provider")
public interface TestService {

    @GetMapping("/providerTest1")
    String test();
}
