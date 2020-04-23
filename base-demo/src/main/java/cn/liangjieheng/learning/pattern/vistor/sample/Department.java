package cn.liangjieheng.learning.pattern.vistor.sample;

/**
 * 部门类，充当访问者角色
 */
public abstract class Department {

    abstract void visit(FulltimeEmployee fulltimeEmployee);

    abstract void visit(ParttimeEmployee parttimeEmployee);
}
