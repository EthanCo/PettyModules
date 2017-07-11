package com.nbhope.hopelauncher.smart.view.dialog

import android.app.Activity
import android.app.Dialog
import android.widget.SeekBar
import com.ethanco.kotlintest.R

/**
 * 进度条PopupWindow
 *
 * @author EthanCo
 * @since 2017/7/10
 * -     ┌─┐       ┌─┐
 * -  ┌──┘ ┴───────┘ ┴──┐
 * -  │                 │
 * -  │       ───       │
 * -  │  ─┬┘       └┬─  │
 * -  │                 │
 * -  │       ─┴─       │
 * -  │                 │
 * -  └───┐         ┌───┘
 * -      │         │
 * -      │         │
 * -      │         │
 * -      │         └──────────────┐
 * -      │                        │
 * -      │                        ├─┐
 * -      │                        ┌─┘
 * -      │                        │
 * -      └─┐  ┐  ┌───────┬──┐  ┌──┘
 * -        │ ─┤ ─┤       │ ─┤ ─┤
 * -        └──┴──┘       └──┴──┘
 * --------------- 神兽保佑 ---------------
 * --------------- 永无BUG! ---------------
 *
 */

/**
 * 显示SeekBar对话框
 * @param initProgress seekBar 初始进度
 * @param maxProgress seekBar 最大进度
 * @param contentViewId 必须包含的id: @+id/seek_bar为SeekBar或其子类
 */
fun showSeekBarDialog(activity: Activity, initProgress: Int, maxProgress: Int, contentViewId: Int, callback: (Int) -> Unit) : Dialog{
    val dialog = Dialog(activity, R.style.TransDialog)
    dialog.setContentView(contentViewId)
    val seekBar = dialog.findViewById(R.id.seek_bar) as SeekBar
    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            callback.invoke(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    })
    seekBar.progress = initProgress
    seekBar.max = maxProgress

    dialog.setCancelable(true)
    dialog.show()
    return dialog
}
