package com.sky.oa.activity

import android.app.Activity
import android.app.AppOpsManager
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.widget.MediaController
import android.widget.VideoView
import com.sky.oa.R
import com.sky.oa.databinding.ActivityCanvasBinding
import com.sky.ui.activity.BaseActivity
import java.io.File

/**
 * Created by SKY on 2017/3/10.
 */
class CanvasActivity : BaseActivity<ActivityCanvasBinding>() {

    override fun getBinding() = ActivityCanvasBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(CanvasView6(this))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        binding.touch.onKeyDown(keyCode, event)
        return super.onKeyDown(keyCode, event)
    }


    private fun setVideo() {
        val video = findViewById<VideoView>(0)
        val mediaController = MediaController(this)
        val file = File("/storage/emulated/0/QQBrowser/视频/时光.mp4")

        if (file.exists()) {
            video?.setVideoPath(file.absolutePath)
            video?.setMediaController(mediaController)
            mediaController.setMediaPlayer(video)
            video?.requestFocus()
            video?.start()
        }
    }

}
