package com.sky.oa.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.common.utils.FileUtils
import com.sky.common.utils.JumpAct
import com.sky.common.utils.LogUtils
import com.sky.oa.adapter.HomeAdapter
import com.sky.oa.adapter.itemtouch.ItemTouchHelperCallback
import com.sky.oa.databinding.FragmentHomeBinding
import com.sky.oa.entity.PoetryEntity
import com.sky.oa.vm.HomeVM
import com.sky.ui.fragment.MVVMFragment
import java.text.Collator
import java.util.*
import kotlin.Comparator

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/3/11 10:25 下午
 * @Version: 1.0
 */
class HomeFragment : MVVMFragment<FragmentHomeBinding, HomeVM>() {
    //    lateinit var adapter: HomeAdapter
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.recycler.isNestedScrollingEnabled = false
        val adapter = HomeAdapter()
        binding.recycler.adapter = adapter

        //设置拖动item
        val helper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        helper.attachToRecyclerView(binding.recycler)

        adapter.onItemClickListener = { v, p ->
            JumpAct.jumpActivity(context, adapter.datas?.get(p)!!.componentName)
        }
        viewModel.getActivities(requireContext())
        viewModel.activitiesData.observe(viewLifecycleOwner, {
            adapter.datas = it
        })
    }

    override fun getViewModel() =
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeVM::class.java]

    /**
     * 计算文本数据的数量
     */
    fun calculationTextLength() {
        val poetries = ArrayList<PoetryEntity>()
        var dir = "Documents"//assets初始路径
        var array: Array<String>? = null//assets取出的目录名称
        val link = LinkedList<String>()
        link.add(dir)
//            val stack = Stack<String>();
//            stack.add(dir)
        var item = PoetryEntity()
        //取出Documents下的所有文本文件
        while (link.isNotEmpty()) {
            dir = link.removeFirst()
            array = context?.assets?.list(dir)
            for (i in array!!) {
                if (i.endsWith(".txt")) {
                    item = item.copy()
//                    item = PoetryEntity()
                    item.name = i.substring(0, i.length - 4)
                    item.path = "$dir/$i"
                    poetries.add(item)
                } else {
                    link.add("$dir/$i")
                }
            }
        }
        val collator = Collator.getInstance(Locale.CHINA)
        poetries.sortWith(Comparator { o1, o2 -> collator.compare(o1.path, o2.path) })

        var text = ""
        for (poetry in poetries) {
//            text = FileUtils.readAssestToByte(context, poetry.path)//字节流也可以但是不建议。
            text = FileUtils.readAssestToChar(context, poetry.path)
                .replace("　".toRegex(), "")
                .replace("\n".toRegex(), "")
            LogUtils.i("${poetry.name}==${text.length}")
        }
        //        String poetry = FileUtils.readAssestToChar(this, "Documents/文学/道家/道德经.txt")
    }
}