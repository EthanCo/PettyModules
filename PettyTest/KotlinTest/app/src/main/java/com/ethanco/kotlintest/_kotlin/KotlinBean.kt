package com.ethanco.kotlintest._kotlin

/**
 * Created by Zhk on 2016/11/7.
 */
class KotlinBean {
    var id: Int = 0
    var name: String = ""

    override fun toString(): String {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'
    }
}