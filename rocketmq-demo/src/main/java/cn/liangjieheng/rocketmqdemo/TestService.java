package cn.liangjieheng.rocketmqdemo;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMsg() {
        this.rocketMQTemplate.convertAndSend("topic1:topic1_tags1", "helloworld");
    }
}
