package com.ethanco.koltintest01

import android.app.Application

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/5/22
 */
class App : Application() {
    val pref by lazy {
        AppPreferences(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}