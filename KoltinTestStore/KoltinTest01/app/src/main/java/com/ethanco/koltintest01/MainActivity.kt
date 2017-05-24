package com.ethanco.koltintest01

import android.os.Bundle
import android.widget.FrameLayout
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

//详见 http://qq157755587.github.io/2015/11/14/kotlin-in-android/
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //dp,sp,px间的转换
        val value = 16.dpToPx()

        //View的动画
        var view = FrameLayout(this)
        view.animateTopMargin(value, value * 2)
        setContentView(view)

        //让Fresco更易用

        //设置View的宽高
        view.setSize(100, 100)

        pref.userName = "Who"
        pref.password = "You"
        println("userName:" + pref.userName)
        println("password:" + pref.password)

        Observable.just("")
                .doOnNext { user -> processUser(user) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //do something
                }, {
                    //handle error
                })
    }

    private fun processUser(user: String?): String {
        return user!!
    }
}
