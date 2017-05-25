package com.ethanco.koltintest02

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager

//http://mp.weixin.qq.com/s/kOIQ7v1B2I_G-qlUxXspzw
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraintLayout = findViewById(R.id.coostraint_layout) as ConstraintLayout

        val constraintSet1 = ConstraintSet()
        constraintSet1.clone(constraintLayout)
        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(this, R.layout.activity_main_alt)

        var changed = false
        findViewById(R.id.button).setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)
            val constraint = if (changed) constraintSet1 else constraintSet2
            constraint.applyTo(constraintLayout)
            changed = !changed
        }
    }
}
