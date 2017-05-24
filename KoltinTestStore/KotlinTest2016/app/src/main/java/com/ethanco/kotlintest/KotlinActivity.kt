package com.ethanco.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.ethanco.kotlintest._kotlin.KotlinBean
import com.ethanco.kotlintest._kotlin.dec
import com.ethanco.kotlintest._kotlin.toast

class KotlinActivity : AppCompatActivity() {
    private var tvInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnTest = findViewById(R.id.btn_test) as Button
        tvInfo = findViewById(R.id.tv_info) as TextView



        btnTest.setOnClickListener {
            //var bean = JavaBean()
            var bean = KotlinBean()
            bean.id = 123
            bean.name = "EthanCo"
            bean.age = 18
            var result = bean.dec(5,6)
            //var result = bean.sum(5, 6)
            //Toast.makeText(this@KotlinActivity, bean.toString(), Toast.LENGTH_SHORT).show()
            toast(bean.toString() + " sum:" + result)
        }
    }
}
