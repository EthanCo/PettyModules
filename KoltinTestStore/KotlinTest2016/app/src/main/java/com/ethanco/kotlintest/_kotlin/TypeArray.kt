package com.ethanco.kotlintest._kotlin

/**
 * 集合泛型
 *
 * @author EthanCo
 * @since 2017/6/9
 */
open class A

open class B : A()
open class C : B()

class TypeArray<in A> {

    //in 修饰了 A，表示 A 是可以作为参数的。
    fun getValue(a: A): Int? {
        return a?.hashCode()
    }

    //这段代码是非法的，因为A 不能被返回
    /*fun getA(a: A): A? {
        return a
    }*/
}