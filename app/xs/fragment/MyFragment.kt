package com.sky.xs.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.common.LogUtils
import com.sky.uidesign.api.IMVPView
import com.sky.uidesign.fragment.BaseFragment
import com.sky.uidesign.fragment.BasePFragment
import com.sky.uidesign.fragment.MVVMFragment
import com.sky.uidesign.presenter.BasePresenter
import com.sky.xs.R

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 6:40 下午
 * @Version: 1.0
 */
class MyFragment : BaseFragment(), IMVPView {
    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onStart() {
        super.onStart()
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onResume() {
        super.onResume()
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onPause() {
        super.onPause()
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onStop() {
        super.onStop()
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.i(javaClass.simpleName + id + Thread.currentThread().stackTrace[2].methodName)
    }
}