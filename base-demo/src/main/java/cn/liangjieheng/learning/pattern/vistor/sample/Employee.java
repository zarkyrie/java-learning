package cn.liangjieheng.learning.pattern.vistor.sample;

/**
 * 员工类，充当元素角色
 */
public interface Employee {

    void accept(Department handler);
}
