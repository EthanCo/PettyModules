package com.ethanco.mydagger2test;

/**
 * Created by EthanCo on 2016/3/19.
 */
public class Person {
    private int age;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
