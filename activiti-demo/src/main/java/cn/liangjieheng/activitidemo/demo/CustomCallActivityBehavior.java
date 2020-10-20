package cn.liangjieheng.activitidemo.demo;

import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.el.ExpressionManager;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.util.ProcessDefinitionUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class CustomCallActivityBehavior extends CallActivityBehavior {
    public CustomCallActivityBehavior(String processDefinitionKey, List<MapExceptionEntry> mapExceptions) {
        super(processDefinitionKey, mapExceptions);
    }

    public CustomCallActivityBehavior(Expression processDefinitionExpression, List<MapExceptionEntry> mapExceptions) {
        super(processDefinitionExpression, mapExceptions);
    }

    @Override
    public void trigger(DelegateExecution execution, String signalName, Object signalData) {
        leave(execution);
    }

    public void execute(DelegateExecution execution) {

        String finalProcessDefinitonKey = null;
        if (processDefinitionExpression != null) {
            finalProcessDefinitonKey = (String) processDefinitionExpression.getValue(execution);
        } else {
            finalProcessDefinitonKey = processDefinitonKey;
        }

        ProcessDefinition processDefinition = findProcessDefinition(finalProcessDefinitonKey, execution.getTenantId());

        // Get model from cache
        Process subProcess = ProcessDefinitionUtil.getProcess(processDefinition.getId());
        if (subProcess == null) {
            throw new ActivitiException("Cannot start a sub process instance. Process model " + processDefinition.getName() + " (id = " + processDefinition.getId() + ") could not be found");
        }

        FlowElement initialFlowElement = subProcess.getInitialFlowElement();
        if (initialFlowElement == null) {
            throw new ActivitiException("No start element found for process definition " + processDefinition.getId());
        }

        // Do not start a process instance if the process definition is suspended
        if (ProcessDefinitionUtil.isProcessDefinitionSuspended(processDefinition.getId())) {
            throw new ActivitiException("Cannot start process instance. Process definition " + processDefinition.getName() + " (id = " + processDefinition.getId() + ") is suspended");
        }

        ProcessEngineConfigurationImpl processEngineConfiguration = Context.getProcessEngineConfiguration();
        ExecutionEntityManager executionEntityManager = Context.getCommandContext().getExecutionEntityManager();
        ExpressionManager expressionManager = processEngineConfiguration.getExpressionManager();

        ExecutionEntity executionEntity = (ExecutionEntity) execution;
        CallActivity callActivity = (CallActivity) executionEntity.getCurrentFlowElement();

        String businessKey = null;

        //获取父流程business key
        String superExecutionBusinessKey =executionEntity.getParent().getBusinessKey();

        if (!StringUtils.isEmpty(callActivity.getBusinessKey())) {
            Expression expression = expressionManager.createExpression(callActivity.getBusinessKey());
            businessKey = expression.getValue(execution).toString();
        } else if (callActivity.isInheritBusinessKey()) {
            ExecutionEntity processInstance = executionEntityManager.findById(execution.getProcessInstanceId());
            businessKey = processInstance.getBusinessKey();
        } else if(StringUtils.isNotBlank(superExecutionBusinessKey)){
            businessKey = finalProcessDefinitonKey+":"+superExecutionBusinessKey;
        }

        ExecutionEntity subProcessInstance = Context.getCommandContext().getExecutionEntityManager().createSubprocessInstance(
                processDefinition,executionEntity, businessKey);
        Context.getCommandContext().getHistoryManager().recordSubProcessInstanceStart(executionEntity, subProcessInstance, initialFlowElement);

        // process template-defined data objects
        Map<String, Object> variables = processDataObjects(subProcess.getDataObjects());

        if (callActivity.isInheritVariables()) {
            Map<String, Object> executionVariables = execution.getVariables();
            for (Map.Entry<String, Object> entry : executionVariables.entrySet()) {
                variables.put(entry.getKey(), entry.getValue());
            }
        }

        // copy process variables
        for (IOParameter ioParameter : callActivity.getInParameters()) {
            Object value = null;
            if (StringUtils.isNotEmpty(ioParameter.getSourceExpression())) {
                Expression expression = expressionManager.createExpression(ioParameter.getSourceExpression().trim());
                value = expression.getValue(execution);

            } else {
                value = execution.getVariable(ioParameter.getSource());
            }
            variables.put(ioParameter.getTarget(), value);
        }

        if (!variables.isEmpty()) {
            initializeVariables(subProcessInstance, variables);
        }

        // Create the first execution that will visit all the process definition elements
        ExecutionEntity subProcessInitialExecution = executionEntityManager.createChildExecution(subProcessInstance);
        subProcessInitialExecution.setCurrentFlowElement(initialFlowElement);

        Context.getAgenda().planContinueProcessOperation(subProcessInitialExecution);

        Context.getProcessEngineConfiguration().getEventDispatcher()
                .dispatchEvent(ActivitiEventBuilder.createProcessStartedEvent(subProcessInitialExecution, variables, false));
    }
}
