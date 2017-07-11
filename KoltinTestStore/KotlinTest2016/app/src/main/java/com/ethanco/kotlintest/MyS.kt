package com.ethanco.kotlintest

/**
 * TODO

 * @author EthanCo
 * *
 * @since 2017/6/22
 */

class MyS {

    companion object {
        internal lateinit var instance: MyS

        fun getInstance(): MyS {
            if (instance == null) {
                instance = MyS()
            }
            return instance
        }
    }
}
