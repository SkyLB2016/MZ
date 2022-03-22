package com.sky.oa.adapter

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
class ArticleAdapter : RecyclerView.Adapter<MvvmHolder<AdapterNoteBinding>>() {
    lateinit var context: Context
    var chapters: MutableList<ChapterEntity> = arrayListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MvvmHolder<AdapterNoteBinding> {
        context = parent.context
        return MvvmHolder(AdapterNoteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MvvmHolder<AdapterNoteBinding>, position: Int) {
        holder.binding.tvDisplay.text = chapters[position].content
        if (position == 0)
            holder.binding.tvDisplay.setPadding(
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