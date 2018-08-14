package com.ethanco.constraintlayouttest

import android.os.Bundle
import android.support.constraint.Placeholder
import android.support.v7.app.AppCompatActivity
import android.view.View



//详见 https://cloud.tencent.com/developer/article/1150341
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeholder)
    }

    fun setContentId(v: View){
        val placeholder = findViewById<Placeholder>(R.id.placeholder)
        placeholder.setContentId(R.id.iv_target)
    }
}
