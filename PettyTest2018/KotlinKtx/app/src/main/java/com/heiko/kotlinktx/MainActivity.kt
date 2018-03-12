package com.heiko.kotlinktx

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.content.edit
import androidx.net.toUri
import androidx.view.doOnPreDraw

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1.
        val myUriString = "http://www.baidu.com"
        //val uri = Uri.parse("myUriString")
        //KTX
        val uri = myUriString.toUri()

        //2.
        val sharedPreferences = getSharedPreferences("Test", Context.MODE_PRIVATE)
        /*sharedPreferences.edit()
                .putBoolean("key", true)
                .apply()*/
        //KTX
        sharedPreferences.edit {
            putBoolean("key", true)
        }

        //3.
        /*val myPath1 = Path()
        val myPath2 = Path()
        val pathDifference = Path(myPath1).apply {
            op(myPath2, Path.Op.DIFFERENCE)
        }

        //KTX
        canvas.apply {
            val checkpoint = save()
            translate(0F, 100F)
            drawPath(pathDifference, myPaint)
            restoreToCount(checkpoint)
        }*/

        //4.
        /*val viewTreeObserver = window.decorView.viewTreeObserver
        viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        viewTreeObserver.removeOnPreDrawListener(this)
                        //actionToBeTriggered()
                        return true
                    }
                })*/

        window.decorView.doOnPreDraw {
            //actionToBeTriggered()
        }
    }
}
