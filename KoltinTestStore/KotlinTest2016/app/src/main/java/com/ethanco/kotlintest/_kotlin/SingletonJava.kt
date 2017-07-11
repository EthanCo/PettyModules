package com.ethanco.kotlintest._kotlin

/**
 * 单例
 * 详见https://www.kotlincn.net/docs/reference/object-declarations.html
 *
 * @author EthanCo
 * @since 2017/5/19
 */
class SingletonJava {
    companion object {

        fun newInstance(): SingletonJava {
            val singleton = SingletonJava()
            return singleton
        }
    }
}