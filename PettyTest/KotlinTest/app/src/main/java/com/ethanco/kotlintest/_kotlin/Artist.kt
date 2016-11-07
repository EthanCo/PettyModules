package com.ethanco.kotlintest._kotlin

/**
 * Created by Zhk on 2016/11/7.
 */

class Artist {
    var id: Long = 0
    var name: String? = null
    var url: String? = null

    override fun toString(): String {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}'
    }
}
