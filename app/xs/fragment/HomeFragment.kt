package com.sky.xs.fragment

import android.os.Bundle
import android.view.View
import com.sky.common.LogUtils
import com.sky.uidesign.api.IMVPView
import com.sky.uidesign.fragment.BaseFragment
import com.sky.uidesign.fragment.BasePFragment
import com.sky.uidesign.fragment.MVVMFragment
import com.sky.uidesign.presenter.BasePresenter
import com.sky.xs.R
import com.sky.xs.entity.PoetryEntity

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 6:40 下午
 * @Version: 1.0
 */
class HomeFragment : BaseFragment(), IMVPView {
    var poetry:PoetryEntity? =null
    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PoetryEntity(
            "测试名称", ".txt",
            "dir/fileName",
            "父级目录",
            10000
        ).also {
            LogUtils.i("name==${it.name}")
        }

    }
}