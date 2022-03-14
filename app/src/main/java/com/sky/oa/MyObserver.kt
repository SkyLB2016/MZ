package com.sky.oa

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.sky.common.utils.LogUtils

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/2/16 5:21 下午
 * @Version: 1.0
 */
class MyObserver :LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        LogUtils.i("observer==onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        LogUtils.i("observer==onPause")
    }
}