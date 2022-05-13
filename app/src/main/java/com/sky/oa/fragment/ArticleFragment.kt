package com.sky.oa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.sky.oa.adapter.NotesFragmentAdapter
import com.sky.oa.databinding.FragmentArticleBinding
import com.sky.oa.repository.NotesRepository
import com.sky.oa.vm.NotesVM
import com.sky.ui.fragment.MVVMFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/3/11 10:25 下午
 * @Version: 1.0
 */
class ArticleFragment : MVVMFragment<FragmentArticleBinding, NotesVM>() {
    lateinit var adapter: NotesFragmentAdapter
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArticleBinding.inflate(inflater, container, false)

    override fun getViewModel(): NotesVM =
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NotesVM(NotesRepository()) as T
            }
        })[NotesVM::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
//        binding.tabs.setupWithViewPager(binding.viewPager)

        adapter = NotesFragmentAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabs.setScrollPosition(position, 0f, true)
            }
        })


        showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                viewModel.getNotesList(requireContext().assets, "Documents/文学")
            }
        }
        viewModel.notesListData.observe(viewLifecycleOwner, {
            adapter.maps = it
            val keys = it.keys
            for (key in keys) {
                binding.tabs.addTab(binding.tabs.newTab().setText(key.split("/").last()))
            }
            disLoading()
        })
    }
}