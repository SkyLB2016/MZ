package com.sky.xs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.sky.common.GsonUtils
import com.sky.uidesign.api.IMVVMView
import com.sky.uidesign.fragment.MVVMFragment
import com.sky.uidesign.viewmodel.MVVMBaseViewModel
import com.sky.xs.adapter.ChildAdapter
import com.sky.xs.databinding.FragmentChildBinding
import com.sky.xs.entity.PoetryEntity

class NotesChildFragment : MVVMFragment<FragmentChildBinding, MVVMBaseViewModel<IMVVMView>>() {
    private lateinit var poetries: MutableList<PoetryEntity>

    companion object {
        const val KEY = "poetry"
        fun newInstance(json: String): Fragment {
            val fragment = NotesChildFragment()
            val bundle = Bundle()
            bundle.putString(KEY, json)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initParameters() {
        if (arguments != null) {
            poetries = GsonUtils.fromJson(
                arguments!!.getString(KEY),
                object : TypeToken<List<PoetryEntity>>() {}.type
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recycler.setHasFixedSize(true)
        viewBinding.recycler.layoutManager = GridLayoutManager(context, 3)
        val adapter = ChildAdapter()
        viewBinding.recycler.adapter = adapter
        adapter.poetries = poetries
        viewBinding.smartRefresh.setRefreshHeader(WaterDropHeader(context))
        viewBinding.smartRefresh.setRefreshFooter(
            BallPulseFooter(context!!).setSpinnerStyle(
                SpinnerStyle.Scale
            )
        )
        viewBinding.smartRefresh.setOnRefreshListener { refreshLayout -> viewBinding.smartRefresh.finishRefresh() }
        viewBinding.smartRefresh.setOnLoadMoreListener { refreshLayout -> viewBinding.smartRefresh.finishLoadMore() }
//        setLoadSir(viewBinding.smartRefresh);
//        showContent();
    }

    override fun getViewBinding(inflater: LayoutInflater?, container: ViewGroup?) =
        FragmentChildBinding.inflate(inflater!!, container, false)

    override fun getViewModel() = MVVMBaseViewModel<IMVVMView>()
}
