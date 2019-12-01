package com.ethanco.dagger2test;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2019/10/1
 */
public class Person {
    Student student;

    public Person(Student student) {
        this.student = student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
