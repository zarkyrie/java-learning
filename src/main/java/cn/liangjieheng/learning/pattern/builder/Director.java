package cn.liangjieheng.learning.pattern.builder;

public class Director {

    private AbstractBuilder builder;

    public Director() {
    }

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public Product construct() {
        this.builder.buildPartA();
        this.builder.buildPartB();
        this.builder.buildPartC();
        return this.builder.getProduct();
    }
}
