package com.ljh.activitidemo.demo;

import org.activiti.engine.*;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.agenda.DefaultActivitiEngineAgenda;
import org.activiti.engine.impl.agenda.EndExecutionOperation;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
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
    private IdentityService identityService;
    @Autowired
    private ProcessEngine processEngine;

    @PostConstruct
    public void init(){
//        processEngine.getProcessEngineConfiguration().setEngineAgendaFactory(CustomActivitiEngineAgenda::new);
        //配置全局监听器
    }

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

    @GetMapping("/start")
    public String start(String key) {
        Map<String, Object> param = new HashMap<>();
//        param.put("name", "test");
//        param.put("form1", "a");
//        param.put("form2", "b");
//        param.put("assigneeList",Arrays.asList("aa","bb"));
//        param.put("assignee1",Arrays.asList("aa1","bb1"));
        String businessKey = "aaaa";
        param.put("aaa","ljh");
//        param.put("businessKey",businessKey);
        identityService.setAuthenticatedUserId("xx");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, businessKey, param);

//        Map<String, String> form = new HashMap<>();
//        form.put("form1", "a");
//        form.put("form2", "b");
//        ProcessInstance processInstance = formService.submitStartFormData(key, form);
//        System.out.println(processInstance);
        ;
//        runtimeService.setVariableLocal();
//        runtimeService.setVariable(processInstance.getId(), "startAction", "start");
//       Task task =taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        return "start";
    }

    @GetMapping("/query")
    public String query(String id) {
//        List<Task> taskList = historyService.createHistoricProcessInstanceQuery().finished().list();
        List<HistoricProcessInstance> historicProcessInstanceList =historyService.createHistoricProcessInstanceQuery().finished().list();
//        runtimeService.createExecutionQuery().processInstanceId("2530").list().forEach(System.out::println);
//        taskService.createTaskQuery().processInstanceId("2530").list().forEach(System.out::println);
//        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery().executionId("5024").list();
//        List<Task> taskList =processEngine.getTaskService().createTaskQuery().processInstanceId(id).list();
//        List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery().finished().processDefinitionKey("testa").list();

        return "query";
    }

    @GetMapping("/finish")
    public String finish(String id) {
//        String[] assignList = {"a","b","c"};
//        Map<String,Object> params= new HashMap<>();
//        params.put("arrayList", Arrays.asList(assignList) );
//        taskService.complete("22527",params);
//        processEngine.getTaskService().addCandidateUser("25011","dept|123");
//        taskService.complete("10016");
//        runtimeService.suspendProcessInstanceById("187502");
//        runtimeService.activateProcessInstanceById("187502");
//        runtimeService.deleteProcessInstance("170358","");
//        taskService.complete("185014");
//        taskService.resolveTask("12509");
//        taskService.delegateTask("12509","lxy");
//        taskService.addCandidateGroup("170222","123");
//        taskService.setVariable("7509","test11","11");
//        taskService.setVariableLocal("7509","test11","test11");
//        System.out.println(taskService.getVariable("7509","test11"));
//        System.out.println(runtimeService.getVariable());
//        runtimeService.messageEventReceived("sucess","2511");
//        processEngine.getTaskService().setVariableLocal("10017","123","123");
//        Map<String,Object> param = new HashMap<>();
//        param.put("num",20);
//        taskService.complete(id,param);
//        runtimeService.suspendProcessInstanceById("20014");
//        runtimeService.activateProcessInstanceById("20014");
        runtimeService.setVariable("20014","111","test");
//        taskService.complete(id);
        return "finish";
    }

    private class CustomActivitiEngineAgenda extends DefaultActivitiEngineAgenda {
        public CustomActivitiEngineAgenda(CommandContext commandContext) {
            super(commandContext);
        }

        @Override
        public void planEndExecutionOperation(ExecutionEntity execution) {
            this.planOperation(new CustomEndExecttionOperation(this.commandContext, execution));
        }
    }

    private class CustomEndExecttionOperation extends EndExecutionOperation {
        public CustomEndExecttionOperation(CommandContext commandContext, ExecutionEntity execution) {
            super(commandContext, execution);
        }

        @Override
        protected int getNumberOfActiveChildExecutionsForExecution(ExecutionEntityManager executionEntityManager, String executionId) {
            return 0;
        }

        @Override
        protected int getNumberOfActiveChildExecutionsForProcessInstance(ExecutionEntityManager executionEntityManager, String processInstanceId) {
           return 0;
        }
    }

    @GetMapping("/form")
    public String form() {
        Map<String, String> param = new HashMap<>();
        param.put("name", "ljh");
        formService.submitStartFormData("test2", param);
        return "form";
    }

    @GetMapping("/getFormData")
    public void getFormData() {
        TaskFormData taskFormData = formService.getTaskFormData("20012");
        System.out.println(taskFormData);
    }

    @GetMapping("/cancelProcessInstance")
    public void cancelProcessInstance(String processInstanceId) {
        runtimeService.deleteProcessInstance(processInstanceId, "test cancel");
    }
}
