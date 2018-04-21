package cn.liangjieheng.learning.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanContext implements BeanFactory {

    private static final Map<String, Object> beans;

    private static final Set<Class<?>> classes;

    static {
        classes = ClassPathScanner.getClass("cn.ljh513");
        beans = new HashMap<>();
        for (Class clazz : classes) {
            beans.put(clazz.getName(), clazz);
        }
    }

    public Class<?> getBean(String beanName) {
        return (Class) beans.get(beanName);
    }

    public Set<Class<?>> listBeans() {
        return classes;
    }
}
