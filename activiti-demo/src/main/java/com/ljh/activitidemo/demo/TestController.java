package com.ljh.activitidemo.demo;

import org.activiti.engine.*;
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
    private FormService formService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FormService formService;

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

    @GetMapping("/start")
    public String start(String key) {
        Map<String, Object> param = new HashMap<>();
        param.put("a", 1);
        param.put("b1", "12312312");
        param.put("collections", Arrays.asList("user1", "user2"));
        param.put("nextTaskAssignee", "ljhljh1");
        runtimeService.startProcessInstanceByKey(key, String.valueOf(new Random().nextInt(100)), param);
        return "start";
    }

    @GetMapping("/query")
    public String query() {
//        List<Task> taskList = taskService.createTaskQuery().taskAssignee("ljhljh1").list();
        runtimeService.createExecutionQuery().processInstanceId("2530").list().forEach(System.out::println);
        taskService.createTaskQuery().processInstanceId("2530").list().forEach(System.out::println);
        return "start";
    }

    @GetMapping("/finish")
    public String finish() {
        taskService.complete("2528");
        return "start";
    }

    @GetMapping("/form")
    public String form(){
        Map<String,String> param = new HashMap<>();
        param.put("name","ljh");
        formService.submitStartFormData("test2",param);
        return "form";
    }
}
