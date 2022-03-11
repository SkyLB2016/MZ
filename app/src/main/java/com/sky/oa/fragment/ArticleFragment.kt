package com.sky.oa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.oa.databinding.FragmentArticleBinding
import com.sky.oa.databinding.FragmentHomeBinding
import com.sky.ui.fragment.BaseFragment

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/3/11 10:25 下午
 * @Version: 1.0
 */
class ArticleFragment : BaseFragment<FragmentArticleBinding>() {
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArticleBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding
    }
}