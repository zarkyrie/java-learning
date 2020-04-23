package cn.liangjieheng.learning.reflect;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ParentClazz {

    public String show(){
        return new ReflectionToStringBuilder(this,ToStringStyle.JSON_STYLE).toString();
    }
}
