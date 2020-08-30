package com.ljh.activitidemo.demo;

import org.activiti.bpmn.model.CallActivity;
import org.activiti.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;
import org.apache.commons.lang3.StringUtils;

public class CustomActivityBehaviorFactory extends DefaultActivityBehaviorFactory {
    @Override
    public CallActivityBehavior createCallActivityBehavior(CallActivity callActivity) {
        String expressionRegex = "\\$+\\{+.+}";
        CallActivityBehavior callActivityBehaviour;

        if (StringUtils.isNotEmpty(callActivity.getCalledElement()) && callActivity.getCalledElement().matches(expressionRegex)) {
            callActivityBehaviour = new CustomCallActivityBehavior(expressionManager.createExpression(callActivity.getCalledElement()), callActivity.getMapExceptions());
        } else {
            callActivityBehaviour = new CustomCallActivityBehavior(callActivity.getCalledElement(), callActivity.getMapExceptions());
        }

        return callActivityBehaviour;
    }
}
