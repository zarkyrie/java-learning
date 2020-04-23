package cn.liangjieheng.learning.pattern.builder;

public class ConcreteBuilder extends AbstractBuilder {

    @Override
    public void buildPartA() {
        this.product.setPartA("A");
    }

    @Override
    public void buildPartB() {
        this.product.setPartB("B");
    }

    @Override
    public void buildPartC() {
        this.product.setPartC("C");
    }
}
