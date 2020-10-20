package cn.liangjieheng.activitidemo.demo;


import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;


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
