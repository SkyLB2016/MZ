package com.sky.oa

import com.sky.common.utils.LogUtils
import com.sky.common.utils.SPUtils
import com.sky.ui.BaseApplication

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/3/11 10:24 下午
 * @Version: 1.0
 */
class App : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        setDebug(BuildConfig.DEBUG)
        LogUtils.isDebug = BuildConfig.DEBUG
        SPUtils.init(this)
    }}