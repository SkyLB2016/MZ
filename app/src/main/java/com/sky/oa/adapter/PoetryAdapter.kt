package com.sky.xs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.oa.R
import com.sky.oa.databinding.AdapterNoteBinding
import com.sky.oa.entity.ChapterEntity
import com.sky.ui.adapter.MvvmHolder

/**
 * Created by libin on 2020/05/13 2:30 PM Wednesday.
 */
class PoetryAdapter : RecyclerView.Adapter<MvvmHolder>() {
    lateinit var binding: AdapterNoteBinding
    lateinit var context: Context

    var chapters: MutableList<ChapterEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvvmHolder {
        context = parent.context
        binding = AdapterNoteBinding.inflate(LayoutInflater.from(parent.context))
        return MvvmHolder(binding.root)
//        return MvvmViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.adapter_note, parent, false)
//        )

    }

    override fun onBindViewHolder(holder: MvvmHolder, position: Int) {
        binding.tvDisplay.text = chapters[position].content
        if (position == 0) binding.tvDisplay.setPadding(
            context.resources.getDimensionPixelSize(R.dimen.wh_16),
            context.resources.getDimensionPixelSize(R.dimen.wh_16),
            context.resources.getDimensionPixelSize(R.dimen.wh_16),
            0
        )

    }

    override fun getItemCount(): Int {
        return if (chapters.isNullOrEmpty()) 0 else chapters.size
    }
}