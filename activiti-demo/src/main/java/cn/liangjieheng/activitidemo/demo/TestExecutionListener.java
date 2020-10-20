package cn.liangjieheng.activitidemo.demo;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class TestExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) {
//        String nextTaskAssignee = (String) delegateExecution.getVariable("nextTaskAssignee");
//        FlowElement flowElement = delegateExecution.getCurrentFlowElement();
//        if (flowElement instanceof UserTask) {
//            ((UserTask) flowElement).setAssignee(nextTaskAssignee);
//        }
    }
}
