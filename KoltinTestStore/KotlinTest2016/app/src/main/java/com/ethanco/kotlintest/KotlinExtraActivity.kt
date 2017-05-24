package com.ethanco.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
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

        tv_info.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        //Unit 表示空函数
        tv_info.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //do somethings
            }
        })

        //运算符重载 输出结果:1 21 41 61 81
        for (i in 1..100 step 20) {
            print("$i ")
        }
        /*等于于这个
        for (i in 1.rangeTo(100) step 20){
            print("$i ")
        }*/

        //TODO 中缀表达式 用infix修饰

        //闭包
        //在Kotlin中与其说一等公民是函数，不如说一等公民是闭包。
        //例如函数、Lambda、if语句、for、when都可以称为闭包。
        //但通常情况下，我们所说的闭包是Lambda表达式

        //自执行闭包 在定义闭包的同时直接执行闭包，一般用于初始化上下文环境。
        { x: Int, y: Int ->
            println("${x + y}")
        }(1, 3)


    }

    //Nothing 表示永远不存在的值
    fun test1(): Nothing {
        throw IllegalAccessError()
    }

    //嵌套函数 函数中再声明函数
    fun test2() {
        val valuesInTheOuterScope = "Kotlin is awesome!"

        fun theFunctionInside(int: Int = 10) {
            println(valuesInTheOuterScope)
            if (int >= 5) theFunctionInside(int - 1)
        }
        theFunctionInside()
    }
}
