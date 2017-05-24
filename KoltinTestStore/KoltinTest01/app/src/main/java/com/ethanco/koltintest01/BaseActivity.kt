package com.ethanco.koltintest01

import android.support.v7.app.AppCompatActivity

/**
 *
 * @author EthanCo
 * @since 2017/5/24
 */
open class BaseActivity : AppCompatActivity() {
    val app by lazy { application as App }
    val pref by lazy { app.pref }
}