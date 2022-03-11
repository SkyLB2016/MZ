package com.sky.xs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.sky.uidesign.fragment.MVVMFragment
import com.sky.xs.adapter.NotesFragmentAdapter
import com.sky.xs.databinding.FragmentNotesBinding
import com.sky.xs.entity.PoetryEntity
import com.sky.xs.vm.NotesViewModel
import kotlinx.coroutines.*

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/10 6:40 下午
 * @Version: 1.0
 */
class NotesFragment : MVVMFragment<FragmentNotesBinding, NotesViewModel>(),
    NotesViewModel.INotesView {
    override fun getViewModel() = NotesViewModel()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup) =
        FragmentNotesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
        adapter = NotesFragmentAdapter(childFragmentManager)
        viewBinding.viewPager.adapter = adapter
        viewBinding.tabs.setupWithViewPager(viewBinding.viewPager)
        showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                viewModel.getPoetries(context!!.assets, "Documents/笔记")
            }
        }
    }

    lateinit var adapter: NotesFragmentAdapter

    override fun loadPoetry(maps: LinkedHashMap<String, ArrayList<PoetryEntity>>) {
        adapter.maps = maps
        disLoading()
    }

}