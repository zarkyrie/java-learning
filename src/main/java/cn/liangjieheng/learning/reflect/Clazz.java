package cn.liangjieheng.learning.reflect;

import java.lang.reflect.InvocationTargetException;

public class Clazz {

    private String name;

    public Clazz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        try {
            Clazz c =  Clazz.class.getConstructor(String.class).newInstance("test");
            System.out.println(c.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
