package cn.liangjieheng.learning.ioc;

import cn.liangjieheng.learning.ioc.annotation.Bean;

@Bean
public class Demo {

    public static void main(String[] args) {
//        BeanFactory beanFactory = new BeanContext();
//        Set<Class<?>> set = beanFactory.listBeans();
//        for (Class c : set) {
//            System.out.println(c);
//        }
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
//        System.out.println(Demo.class.getClassLoader().getResource(""));
//        System.out.println(ClassLoader.getSystemResource(""));
//        System.out.println(Demo.class.getResource(""));
//        System.out.println(Demo.class.getResource("/"));
//        //Class文件所在路径
//        System.out.println(new File("/").getAbsolutePath());
//        System.out.println(System.getProperty("user.dir"));

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
    }
}
