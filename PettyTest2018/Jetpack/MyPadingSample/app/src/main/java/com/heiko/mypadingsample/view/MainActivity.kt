package com.heiko.mypadingsample.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.heiko.mypadingsample.R
import com.heiko.mypadingsample.view.adapter.GatewayAdapter
import com.heiko.mypadingsample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

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

        viewModel.allGateways.observe(this, Observer { adapter.submitList(it) })
    }
}
