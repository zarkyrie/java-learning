package com.ljh.activitidemo.demo;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;

public class Nodifiter implements JavaDelegate {
    @Autowired
    private Expression expression;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println(ToStringBuilder.reflectionToString(expression, ToStringStyle.JSON_STYLE));
    }
}

