package cn.liangjieheng.learning.ioc;

import cn.liangjieheng.learning.ioc.annotation.Bean;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public class AutoClassPathScanner {

    public static Set<Class<?>> getClazzes() {
        Set<Class<?>> classSet = new HashSet<>();
        System.out.println(ClassLoader.getSystemResource("").getPath());
        String path = ClassLoader.getSystemResource("").getPath();
        try {
            findClazz(path, "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classSet;
    }

    private static void findClazz(String path, String prefix) throws ClassNotFoundException {
        File parent = new File(path);
        if (parent.isDirectory()) {
            File[] files = parent.listFiles();
            for (File f : files) {
                findClazz(f.getPath(), "".equals(prefix) ? f.getName() : prefix + "." + f.getName());
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
                            if (annotation.annotationType().equals(Bean.class)) {
                                System.out.println("加载成功:" + clazz.getName());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        getClazzes();
    }
}
