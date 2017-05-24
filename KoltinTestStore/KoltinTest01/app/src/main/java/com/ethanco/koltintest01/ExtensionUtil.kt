package com.ethanco.koltintest01

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Extension
 *
 * @author EthanCo
 * @since 2017/5/22
 */
fun Int.dpToPx(): Int {
    if (toInt() in intArrayOf(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)) {
        return this
    }
    return (this * Global.density).toInt() //这里的Gloabl.density是在应用启动时获取的
}

fun View.animateTopMargin(valueFromInDP: Int, valueToInDP: Int, duration: Long = 300) {
    val animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            val params = layoutParams as ViewGroup.MarginLayoutParams
            val from = valueFromInDP.dpToPx()
            val to = valueToInDP.dpToPx()
            params.topMargin = from + ((to - from) * interpolatedTime).toInt()
            layoutParams = params
        }
    }
    animation.duration = duration
    startAnimation(animation)
}

fun View.setSize(width: Int, height: Int) {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
}
