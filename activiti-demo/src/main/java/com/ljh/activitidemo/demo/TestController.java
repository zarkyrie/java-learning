package com.ljh.activitidemo.demo;

import org.activiti.api.process.model.payloads.StartProcessPayload;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private ProcessRuntime processRuntime;

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

    @GetMapping("/start")
    public String start(String key) {
        StartProcessPayload startProcessPayload = new StartProcessPayload();
        Map<String, Object> param = new HashMap<>();
        param.put("a", 1);
        param.put("b1", "12312312");
        param.put("collections", Arrays.asList("user1", "user2"));
        runtimeService.startProcessInstanceByKey(key, String.valueOf(new Random().nextInt(100)), param);
        return "start";
    }

    @GetMapping("/query")
    public String query() {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee("user1").list();
        repositoryService.createDeployment().category("123").deploy();
        return "start";
    }

    @GetMapping("/finish")
    public String finish() {
        taskService.complete("95011");
        return "start";
    }
}
