package com.sky.oa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.common.utils.LogUtils
import com.sky.oa.databinding.FragmentHomeBinding
import com.sky.oa.entity.PoetryEntity
import com.sky.ui.fragment.BaseFragment

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/3/11 10:25 下午
 * @Version: 1.0
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tv.text = "示例页面"
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