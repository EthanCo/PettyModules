package com.ethanco.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ethanco.kotlintest._kotlin.KotlinBean
import com.ethanco.kotlintest._kotlin.dec
import com.ethanco.kotlintest._kotlin.toast
import kotlinx.android.synthetic.main.activity_main.*

class KotlinExtraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener {
            //var bean = JavaBean()
            var bean = KotlinBean()
            bean.id = 123
            bean.name = "EthanCo"
            bean.age = 18
            var result = bean.dec(5, 6)
            //bean.test1 { m -> toast(m, 1) }
            //var result = bean.sum(5, 6)
            //Toast.makeText(this@KotlinActivity, bean.toString(), Toast.LENGTH_SHORT).show()
            toast(bean.toString() + " sum:" + result)
        }

        btn_callback.setOnClickListener {
            var bean = KotlinBean()
            bean.testcallback1 { m -> toast(m) }

            val result = bean.testcallback2(1, 2, { value1, value2 -> value1 + value2 })
            toast("计算结果" + result.toString())
        }
    }
}
