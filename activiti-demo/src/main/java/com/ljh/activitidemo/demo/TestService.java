package com.ljh.activitidemo.demo;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class TestService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    public void start() {
        Map<String,Object> param = new HashMap<>();
        param.put("a",1);
        param.put("b1","12312312");
        runtimeService.startProcessInstanceByKey("oneTaskProcess", String.valueOf(new Random().nextInt(100)),param);
    }

    public void query() {
        List<Task> taskList = taskService.createTaskQuery().list();
    }

    public void finish() {
        taskService.complete("2505");
    }
}
