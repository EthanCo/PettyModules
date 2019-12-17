package com.heiko.saveinstancestacetest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var btn01: Button
    private lateinit var btn02: Button

    private var btn02Enable = false
    protected var imageArr = arrayOfNulls<String>(2)
    protected var fileArr = arrayOfNulls<File>(2)


    companion object {
        const val TAG = "Z-Main"
        const val KEY_Enable = "KEY_Enable"
        const val KEY_ARR = "KEY_ARR"
        const val KEY_FILE = "KEY_FILE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        if (savedInstanceState != null) {
            imageArr = savedInstanceState.getStringArray(KEY_ARR) as Array<String?>
            btn02Enable = savedInstanceState.getBoolean(KEY_Enable, btn02Enable)
            for (i in fileArr.indices) {
                val serializable = savedInstanceState.getSerializable(KEY_FILE + i)
                if (serializable != null) {
                    fileArr[i] = serializable as File
                }
            }
            for (key in savedInstanceState.keySet()) {
                Log.i(
                    TAG,
                    "Key=" + key + ", content=" + savedInstanceState?.getString(key)
                )
            }
        }

        Log.i(TAG, "imageArr[0]:" + imageArr[0] + " imageArr[1]:" + imageArr[1])
        Log.i(TAG, "fileArr[0]:" + fileArr[0] + " fileArr[1]:" + fileArr[1])
        Log.i(TAG, "fileArr2[0]:" + viewModel.fileArr2[0] + " fileArr2[1]:" + viewModel.fileArr2[1])

        btn01 = findViewById(R.id.btn_01)
        btn02 = findViewById(R.id.btn_02)

        btn02.isEnabled = btn02Enable

        btn01.setOnClickListener {
            btn02Enable = !btn02.isEnabled
            btn02.isEnabled = btn02Enable

            imageArr[0] = "hello"
            imageArr[1] = "world"

            fileArr[0] = File("gg")
            fileArr[1] = File("smd")

            viewModel.fileArr2[0] = File("vvv")
            viewModel.fileArr2[1] = File("eno")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(KEY_Enable, btn02Enable)
        outState.putStringArray(KEY_ARR, imageArr)
        for (i in fileArr.indices) {
            if (fileArr[i] != null) {
                outState.putSerializable(KEY_FILE + i, fileArr[i])
            }
        }

        for (key in outState.keySet()) {
            Log.i(TAG, "Key=" + key + ", content=" + outState.get(key))
        }
    }
}
