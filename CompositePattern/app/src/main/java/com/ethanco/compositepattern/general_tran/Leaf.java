package com.ethanco.compositepattern.general_tran;

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

    @Override
    public void addChild(Component child) {
        throw new UnsupportedOperationException("叶子节点没有子节点");
    }

    @Override
    public void removeChild(Component child) {
        throw new UnsupportedOperationException("叶子节点没有子节点");
    }

    @Override
    public Component getChildren(int index) {
        throw new UnsupportedOperationException("叶子节点没有子节点");
    }
}
