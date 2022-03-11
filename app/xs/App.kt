package com.sky.xs

import com.sky.common.LogUtils
import com.sky.common.SPUtils
import com.sky.uidesign.BaseApplication

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/12 4:36 下午
 * @Version: 1.0
 */
class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        setDebug(BuildConfig.DEBUG)
        LogUtils.isDebug = BuildConfig.DEBUG
        SPUtils.init(this)
    }
}