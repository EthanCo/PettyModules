package com.ethanco.compositepattern.general_tran;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体枝干节点
 * 定义有子节点的那些枝干节点的行为，存储子节点，
 * 在Component接口中实现与子节点有关的操作。
 * Created by EthanCo on 2016/7/17.
 */
public class Composite extends Component {
    private List<Component> components = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        System.out.println(name);
        if (null != components) {
            for (Component c : components) {
                c.doSomething();
            }
        }
    }

    /**
     * 添加子节点
     *
     * @param child
     */
    public void addChild(Component child) {
        components.add(child);
    }

    /**
     * 移除子节点
     *
     * @param child
     */
    public void removeChild(Component child) {
        components.remove(child);
    }

    /**
     * 获取子节点
     *
     * @param index
     * @return
     */
    public Component getChildren(int index) {
        return components.get(index);
    }
}
