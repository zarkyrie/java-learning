package com.ljh.activitidemo.demo;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.springframework.stereotype.Component;

@Component
public class TestEventListener implements ActivitiEventListener {
    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        switch (activitiEvent.getType()){
            case TASK_CREATED:
                System.out.println("TASK_CREATED");
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
