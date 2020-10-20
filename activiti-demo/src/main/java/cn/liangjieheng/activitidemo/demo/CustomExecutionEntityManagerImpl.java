package cn.liangjieheng.activitidemo.demo;

import org.activiti.engine.delegate.event.impl.ActivitiEventBuilder;
import org.activiti.engine.history.DeleteReason;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManagerImpl;
import org.activiti.engine.impl.persistence.entity.data.ExecutionDataManager;

import java.util.List;

public class CustomExecutionEntityManagerImpl extends ExecutionEntityManagerImpl {

    public CustomExecutionEntityManagerImpl(ProcessEngineConfigurationImpl processEngineConfiguration, ExecutionDataManager executionDataManager) {
        super(processEngineConfiguration, executionDataManager);
    }

    protected void deleteProcessInstanceCascade(ExecutionEntity execution, String deleteReason, boolean deleteHistory){
        // fill default reason if none provided
        if (deleteReason == null) {
            deleteReason = DeleteReason.PROCESS_INSTANCE_DELETED;
        }

        for (ExecutionEntity subExecutionEntity : execution.getExecutions()) {
            if (subExecutionEntity.isMultiInstanceRoot()) {
                for (ExecutionEntity miExecutionEntity : subExecutionEntity.getExecutions()) {
                    if (miExecutionEntity.getSubProcessInstance() != null) {
                        deleteProcessInstanceCascade(miExecutionEntity.getSubProcessInstance(), deleteReason, deleteHistory);
                    }
                }

            } else if (subExecutionEntity.getSubProcessInstance() != null) {
                deleteProcessInstanceCascade(subExecutionEntity.getSubProcessInstance(), deleteReason, deleteHistory);
            }
        }

        getTaskEntityManager().deleteTasksByProcessInstanceId(execution.getId(), deleteReason, deleteHistory);

        if (getEventDispatcher().isEnabled()) {
            getEventDispatcher().dispatchEvent(ActivitiEventBuilder.createCancelledEvent(execution.getProcessInstanceId(),
                    execution.getProcessInstanceId(), null, deleteReason));
        }

        // delete the execution BEFORE we delete the history, otherwise we will
        // produce orphan HistoricVariableInstance instances

        ExecutionEntity processInstanceExecutionEntity = execution.getProcessInstance();
        if (processInstanceExecutionEntity == null) {
            return;
        }

        List<ExecutionEntity> childExecutions = collectChildren(execution.getProcessInstance());
        for (int i=childExecutions.size()-1; i>=0; i--) {
            ExecutionEntity childExecutionEntity = childExecutions.get(i);
            deleteExecutionAndRelatedData(childExecutionEntity, deleteReason, false);
        }

        deleteExecutionAndRelatedData(execution, deleteReason, false);

        //删除父流程call activity
        if(execution.getSuperExecution()!=null){
            deleteExecution(execution);
            // Continue process (if not a standalone task)
            if (execution.getSuperExecution().getId() != null) {
                ExecutionEntity executionEntity = processEngineConfiguration.getExecutionEntityManager().findById(execution.getSuperExecution().getId());
                Context.getAgenda().planTriggerExecutionOperation(executionEntity);
            }
        }

        if (deleteHistory) {
            getHistoricProcessInstanceEntityManager().delete(execution.getId());
        }

        getHistoryManager().recordProcessInstanceEnd(processInstanceExecutionEntity.getId(), deleteReason, null);
        processInstanceExecutionEntity.setDeleted(true);
    }

    private void deleteExecution(ExecutionEntity execution){
        ExecutionEntity superExecution = execution.getSuperExecution();
        delete(superExecution);
    }
}
