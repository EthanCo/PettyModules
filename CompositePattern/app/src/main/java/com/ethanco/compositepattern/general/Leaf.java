package com.ethanco.compositepattern.general;

/**
 * 具体叶子节点
 * 在组合中表示叶子节点对象，叶子节点没有子节点，在组合中定义节点的行为。
 * Created by EthanCo on 2016/7/17.
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        System.out.println(name);
    }
}
