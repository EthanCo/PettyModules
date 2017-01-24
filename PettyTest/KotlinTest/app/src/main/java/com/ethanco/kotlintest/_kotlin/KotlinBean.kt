package com.ethanco.kotlintest._kotlin

/**
 * Created by Zhk on 2016/11/7.
 */
class KotlinBean {
    var id: Int = 0
    var name: String = ""

    //默认情况下即实现了get和set，如果要自定义get和set，如下处理
    var age :Int
    //get() = age //java.lang.StackOverflowError
    get() = 15
    set(value) {age+666}

    override fun toString(): String {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'
    }
}