package com.ljh.activitidemo.demo;

import org.activiti.engine.delegate.DelegateHelper;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TestTaskListener implements TaskListener {
    private Expression expression;

    @Override
    public void notify(DelegateTask delegateTask) {
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(delegateTask.getProcessInstanceId() + ":" + expression.getExpressionText());
//            System.out.println(delegateTask.getProcessDefinitionId() + ":" + expression.getExpressionText());
//            System.out.println(delegateTask.getProcessDefinitionId() + ":" + DelegateHelper.getFieldExpression(delegateTask,"expression"));
        }
    }
}
