package com.ljh.activitidemo.demo;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/***
 * @description 流程事件侦听
 * @author LHJ
 * @date 2019/12/7 14:05
 */
@Component
public class WorkflowEventListener implements ActivitiEventListener {

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public void onEvent(ActivitiEvent event) {
        System.out.println(event.getType());
        System.out.println(ToStringBuilder.reflectionToString(event, ToStringStyle.JSON_STYLE));
    }



}
