package com.heiko.aspectjtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.heiko.aspectjtest.anno.TimeSpend

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTest = findViewById<Button>(R.id.btn_test)
        btnTest.setOnClickListener {
            /*ClickAspect clickAspect = new ClickAspect();
                clickAspect.clickMethod();*/
            Log.i("Z-Test", "OnClick")
            Toast.makeText(this@KotlinActivity, "click Me !", Toast.LENGTH_SHORT).show()
        }

        val btnTestTime = findViewById<Button>(R.id.btn_test_time)
        btnTestTime.setOnClickListener { attemptLogin() }

        val btnGoKotlin = findViewById<Button>(R.id.btn_go_kotlin)
        btnGoKotlin.setOnClickListener { }
    }

    @TimeSpend("登录")
    private fun attemptLogin() {
        val sHasLogin = true
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
    }
}
