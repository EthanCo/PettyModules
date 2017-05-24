package com.ethanco.kotlintest._java;

/**
 * Created by Zhk on 2016/11/7.
 */

public class JavaBean {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
