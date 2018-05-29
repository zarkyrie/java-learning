package cn.liangjieheng.learning.pattern.builder;

public abstract class AbstractBuilder {

    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    public Product getProduct() {
        return this.product;
    }
}
