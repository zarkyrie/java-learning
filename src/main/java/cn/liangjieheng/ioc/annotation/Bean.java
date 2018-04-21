package cn.liangjieheng.ioc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface Bean {

    String[] value() default {};
}
