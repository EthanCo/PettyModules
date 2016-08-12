package com.ethanco.compositepattern.general;

/**
 * 抽象根节点
 * 抽象根结果，为组合中的对象声明接口。
 * 在适合的情况下，实现所有类共有接口的缺省行为。
 * 声明一个接口用于访问和管理Component的子节点。
 * 可以在递归结构中定义一个接口，用于访问一个父节点，并在适合的情况下实现它。
 * Created by EthanCo on 2016/7/17.
 */
public abstract class Component {
    protected String name; //节点名

    public Component(String name) {
        this.name = name;
    }

    /**
     * 具体的逻辑方法由子类实现
     */
    public abstract void doSomething();
}
