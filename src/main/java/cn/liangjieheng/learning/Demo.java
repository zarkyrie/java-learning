package cn.liangjieheng.learning;

import java.io.File;
import java.lang.annotation.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Demo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanContext();
        Set<Class<?>> set = beanFactory.listBeans();
        for (Class c : set) {
            System.out.println(c);
        }
        Object bean = beanFactory.getBean("hello");
        String name = ((Person) bean).getName();
        System.out.println(name);
    }
}

@Bean("hello")
class Person {

    private String name = "112233";

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@interface Bean {

    String value() default "";
}

interface BeanFactory {

    Object getBean(String beanName);

    Set<Class<?>> listBeans();
}

class BeanContext implements BeanFactory {
    private static final ConcurrentHashMap<String, Object> beans;
    private static final Set<Class<?>> classes;

    static {
        classes = AutoClassPathScanner.getClazz();
        beans = new ConcurrentHashMap<>();
        try {
            for (Class clazz : classes) {
                beans.put(((Bean) clazz.getAnnotation(Bean.class)).value(), clazz.getDeclaredConstructor().newInstance());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Object getBean(String beanName) {
        return beans.get(beanName);
    }

    public Set<Class<?>> listBeans() {
        return classes;
    }
}

class AutoClassPathScanner {

    public static Set<Class<?>> getClazz() {
        Set<Class<?>> classSet = new HashSet<>();
        System.out.println(ClassLoader.getSystemResource("").getPath());
        String path = ClassLoader.getSystemResource("").getPath();
        try {
            findClazz(path, "", classSet);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classSet;
    }

    private static void findClazz(String path, String prefix, Set<Class<?>> classSet) throws ClassNotFoundException {
        File parent = new File(path);
        if (parent.isDirectory()) {
            File[] files = parent.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                findClazz(f.getPath(), "".equals(prefix) ? f.getName() : prefix + "." + f.getName(), classSet);
            }
        } else {
            String fileName = parent.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if ("class".equals(suffix)) {
                if (!parent.isDirectory()) {
                    Class clazz = Class.forName(prefix.replace(".class", ""));
                    if (!clazz.isAnnotation()) {
                        Annotation[] annotations = clazz.getAnnotations();
                        for (Annotation annotation : annotations) {
                            if (annotation.annotationType().equals(cn.liangjieheng.learning.Bean.class)) {
                                classSet.add(clazz);
                                System.out.println("加载成功:" + clazz.getName());
                            }
                        }
                    }
                }
            }
        }
    }
}
