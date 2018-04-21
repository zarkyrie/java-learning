package cn.liangjieheng.ioc;


import cn.liangjieheng.ioc.annotation.Bean;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class ClassPathScanner {

    public static Set<Class<?>> getClass(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        String packageDir = packageName.replace(".", File.separator);
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDir);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                findClasses(packageName, filePath, classSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classSet;
    }

    private static void findClasses(String packageName, String filePath, Set<Class<?>> classes) {
        File dir = new File(filePath);
        if (!dir.isDirectory()) {
            throw new RuntimeException("is not directory");
        }
        File[] files = dir.listFiles();
        try {
            for (File file : files) {
                if (!file.isDirectory()) {
                    Class clazz = Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6));
                    if (!clazz.isAnnotation()) {
                        Annotation[] annotations = clazz.getAnnotations();
                        for (Annotation annotation : annotations) {
                            if (annotation.annotationType().equals(Bean.class)) {
                                classes.add(clazz);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
