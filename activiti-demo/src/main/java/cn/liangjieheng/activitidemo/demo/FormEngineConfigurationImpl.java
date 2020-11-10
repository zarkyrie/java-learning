//package cn.liangjieheng.activitidemo.demo;
//
//import com.deepexi.workflow.core.form.*;
//import com.deepexi.workflow.core.form.deployer.CustomParsedDeploymentBuilderFactory;
//import com.deepexi.workflow.core.form.impl.FormSubmittedEntityManagerImpl;
//import com.deepexi.workflow.core.form.impl.FormSubmittedServiceImpl;
//import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
//import org.activiti.form.engine.FormEngineConfiguration;
//import org.activiti.form.engine.impl.deployer.CachingAndArtifactsManager;
//import org.activiti.form.engine.impl.deployer.FormDeploymentHelper;
//import org.activiti.form.engine.impl.persistence.entity.FormDeploymentEntityManagerImpl;
//import org.activiti.form.engine.impl.persistence.entity.FormEntityManagerImpl;
//import org.activiti.form.engine.impl.persistence.entity.ResourceEntityManagerImpl;
//import org.activiti.form.engine.impl.persistence.entity.SubmittedFormEntityManagerImpl;
//import org.activiti.form.engine.impl.persistence.entity.data.impl.MybatisFormDataManager;
//import org.activiti.form.engine.impl.persistence.entity.data.impl.MybatisFormDeploymentDataManager;
//import org.activiti.form.engine.impl.persistence.entity.data.impl.MybatisResourceDataManager;
//import org.activiti.form.engine.impl.persistence.entity.data.impl.MybatisSubmittedFormDataManager;
//
///***
// * @description 表单引擎配置
// * @author LHJ
// * @date 2020/3/2 11:42
// */
//public class FormEngineConfigurationImpl extends FormEngineConfiguration {
//
//
//    protected FormSubmittedService formSubmittedService = new FormSubmittedServiceImpl();
//    protected FormSubmittedDataManager formSubmittedDataManager;
//    protected FormSubmittedEntityManager formSubmittedEntityManager;
//
//    @Override
//    public void initIdGenerator() {
//        if (idGenerator == null) {
//            idGenerator = new FormDbIdGenerator();
//        }
//        setDataSource(dataSource);
//    }
//
//    public void setIdGenerator(ProcessEngineConfigurationImpl processEngineConfiguration) {
//        if (idGenerator == null) {
//            idGenerator = new FormDbIdGenerator();
//        }
//        ((FormDbIdGenerator) idGenerator).setConfigure(processEngineConfiguration);
//    }
//
//    @Override
//    protected void initServices() {
//        initService(repositoryService);
//        initService(formService);
//        initService(formSubmittedService);
//    }
//
//    @Override
//    public void initDataManagers() {
//        if (deploymentDataManager == null) {
//            deploymentDataManager = new MybatisFormDeploymentDataManager(this);
//        }
//        if (formDataManager == null) {
//            formDataManager = new MybatisFormDataManager(this);
//        }
//        if (resourceDataManager == null) {
//            resourceDataManager = new MybatisResourceDataManager(this);
//        }
//        if (submittedFormDataManager == null) {
//            submittedFormDataManager = new MybatisSubmittedFormDataManager(this);
//        }
//
//
//        if (formSubmittedDataManager == null) {
//            formSubmittedDataManager = new MybatisFormSubmittedDataManager(this);
//        }
//    }
//
//    @Override
//    public void initEntityManagers() {
//        if (deploymentEntityManager == null) {
//            deploymentEntityManager = new FormDeploymentEntityManagerImpl(this, deploymentDataManager);
//        }
//        if (formEntityManager == null) {
//            formEntityManager = new FormEntityManagerImpl(this, formDataManager);
//        }
//        if (resourceEntityManager == null) {
//            resourceEntityManager = new ResourceEntityManagerImpl(this, resourceDataManager);
//        }
//        if (submittedFormEntityManager == null) {
//            submittedFormEntityManager = new SubmittedFormEntityManagerImpl(this, submittedFormDataManager);
//        }
//        if (formSubmittedEntityManager == null) {
//            formSubmittedEntityManager = new FormSubmittedEntityManagerImpl(this, formSubmittedDataManager) {
//            };
//        }
//    }
//
//    @Override
//    public void initDmnDeployerDependencies() {
//        if (parsedDeploymentBuilderFactory == null) {
//            //使用自定义部署构造工厂
//            parsedDeploymentBuilderFactory = new CustomParsedDeploymentBuilderFactory();
//        }
//        if (parsedDeploymentBuilderFactory.getFormParseFactory() == null) {
//            parsedDeploymentBuilderFactory.setFormParseFactory(formParseFactory);
//        }
//
//        if (formDeploymentHelper == null) {
//            formDeploymentHelper = new FormDeploymentHelper();
//        }
//
//        if (cachingAndArtifactsManager == null) {
//            cachingAndArtifactsManager = new CachingAndArtifactsManager();
//        }
//    }
//
//
//    public FormSubmittedService getFormSubmittedService() {
//        return formSubmittedService;
//    }
//
//    public FormSubmittedDataManager getFormSubmittedDataManager() {
//        return formSubmittedDataManager;
//    }
//
//    public void setFormSubmittedDataManager(FormSubmittedDataManager formSubmittedDataManager) {
//        this.formSubmittedDataManager = formSubmittedDataManager;
//    }
//
//    public FormSubmittedEntityManager getFormSubmittedEntityManager() {
//        return formSubmittedEntityManager;
//    }
//
//    public void setFormSubmittedEntityManager(FormSubmittedEntityManager formSubmittedEntityManager) {
//        this.formSubmittedEntityManager = formSubmittedEntityManager;
//    }
//}
