package cn.liangjieheng.learning.reflect;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.InvocationTargetException;

public class Clazz extends ParentClazz{

    private String name;

    private String no;

    public Clazz(String name, String no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
//        try {
//            Clazz c = Clazz.class.getConstructor(String.class,String.class).newInstance("test","01");
//            System.out.println(c.getName());
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        Clazz cl = new Clazz("ljh", "01");
        System.out.println(cl.show());
//        System.out.println(new ReflectionToStringBuilder(cl,ToStringStyle.JSON_STYLE).toString());

    }
}
