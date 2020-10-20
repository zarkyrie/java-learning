package cn.liangjieheng.activitidemo.demo;

import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisExecutionDataManager;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 针对SpringProcessEngineConfiguration的扩展配置
 * @author yangxi
 */
@Component
public class WorkflowConfigurationConfigurer implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private WorkflowEventListener workflowEventListener;

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {

        //添加数据源
        processEngineConfiguration.setDataSource(dataSource);

        //配置全局监听器
        List<ActivitiEventListener> eventListeners =new ArrayList<>();
        eventListeners.add(workflowEventListener);
        processEngineConfiguration.setEventListeners(eventListeners);

        //设置全局的mapper
        Set<Class<?>> mappers = processEngineConfiguration.getCustomMybatisMappers();
        if (mappers == null) {
            mappers = new HashSet<>();
        }
//        mappers.add(CustomActivitiDatabaseMapper.class);
//        processEngineConfiguration.setCustomMybatisMappers(mappers);
//        Set<String> mapperXmls = processEngineConfiguration.getCustomMybatisXMLMappers();
//        if (mapperXmls == null) {
//            mapperXmls = new HashSet<>();
//        }
//        mapperXmls.add("mapper/CustomActivitiDatabaseMapper.xml");
//        processEngineConfiguration.setCustomMybatisXMLMappers(mapperXmls);
//
//
//        //自定义表单类型
//        List<AbstractFormType> customFormTypes = new ArrayList<>();
//        //下拉框
//        customFormTypes.add(new DropdownFormType());
//        customFormTypes.add(new CheckboxGroupFormType());
//        customFormTypes.add(new FileFormType());
//        customFormTypes.add(new MultiTextFormType());
//        customFormTypes.add(new RadioGroupFormType());
//        customFormTypes.add(new TableFormFormType());
//        customFormTypes.add(new RichTextFormType());
//        customFormTypes.add(new TimeRangeFormType());
//        customFormTypes.add(new CheckboxFormType());
//        processEngineConfiguration.setCustomFormTypes(customFormTypes);
//
//
//        //设置自定义流程处理器
//        processEngineConfiguration.setProcessInstanceHelper(new CustomProcessInstanceHelper());
//        BpmnXMLConverter.addConverter(new CustomCallActivityXMLConverter());
//        BpmnXMLConverter.addConverter(new CustomUserTaskXMLConverter());
//        BpmnXMLConverter.addConverter(new CustomStartEventXMLConverter());
//
//        //设置自定义执行计划表
//        processEngineConfiguration.setEngineAgendaFactory(new CustomActivitiEngineAgendaFactory());
        processEngineConfiguration.setExecutionDataManager(new MybatisExecutionDataManager(processEngineConfiguration));
        processEngineConfiguration.setExecutionEntityManager(new CustomExecutionEntityManagerImpl(processEngineConfiguration,processEngineConfiguration.getExecutionDataManager()));
        // 设置自定义行为工厂
        processEngineConfiguration.setActivityBehaviorFactory(new CustomActivityBehaviorFactory());
    }



}
