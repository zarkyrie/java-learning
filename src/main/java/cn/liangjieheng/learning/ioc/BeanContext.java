package cn.liangjieheng.learning.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BeanContext implements BeanFactory {

    private static final ConcurrentHashMap<String, Object> beans;

    private static final Set<Class<?>> classes;

    static {
        classes = ClassPathScanner.getClazzes("cn.liangjieheng.learning.ioc");
        beans = new ConcurrentHashMap<>();
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
