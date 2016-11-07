package com.ethanco.kotlintest._kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ethanco.kotlintest.R

/**
 * Created by Zhk on 2016/11/7.
 */

class TestActivity : AppCompatActivity() {
    private var tvInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTest = findViewById(R.id.btn_test) as Button
        tvInfo = findViewById(R.id.tv_info) as TextView

        btnTest.setOnClickListener {
            //Toast.makeText(this@TestActivity, "Hello", Toast.LENGTH_SHORT).show()
            toast("Hello", Toast.LENGTH_SHORT)
        }
    }
}
