package com.heiko.mypadingsample.view

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.heiko.mypadingsample.R
import com.heiko.mypadingsample.view.adapter.GatewayAdapter
import com.heiko.mypadingsample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 详见
 * https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650825603&idx=1&sn=bbc0195073aca3706e855c9ed7b76b0b&chksm=80b7b71db7c03e0bc7e8087c6fa27c492168552aea4bff04fd9a1f85402e7ee2c4760fbf2660&mpshare=1&scene=1&srcid=0606yOuokgDpC3OXFEVXU1I1#rd
 * https://blog.csdn.net/mq2553299/article/details/80788692
 * https://www.sohu.com/a/234204678_611601
 */
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(application) as T

        }).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = GatewayAdapter()
        list.adapter = adapter

        //viewModel.allGateways.observe(this, Observer { adapter.submitList(it) })
        /*viewModel.allGatewayRxJava.subscribe({ result ->
            adapter.submitList(result)
        }, { throwable ->
            Toast.makeText(MainActivity@ this, "error:" + throwable.message, Toast.LENGTH_SHORT)
        })*/

        viewModel.allGatewayRxJava2.subscribe({ result ->
            adapter.submitList(result)
        }, { throwable ->
            Toast.makeText(MainActivity@ this, "error:" + throwable.message, Toast.LENGTH_SHORT)
        })
    }
}
