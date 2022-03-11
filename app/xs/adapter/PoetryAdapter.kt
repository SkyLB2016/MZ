package com.sky.xs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.uidesign.adapter.MvvmViewHolder
import com.sky.xs.R
import com.sky.xs.entity.ChapterEntity
import kotlinx.android.synthetic.main.adapter_poetry.view.*
import java.util.*

/**
 * Created by libin on 2020/05/13 2:30 PM Wednesday.
 */
class PoetryAdapter : RecyclerView.Adapter<MvvmViewHolder>() {

    var chapters: MutableList<ChapterEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvvmViewHolder {

        return MvvmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_poetry, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MvvmViewHolder, position: Int) {
        with(holder!!.itemView) {
            tvDisplay.text = chapters[position].content
            if (position == 0) tvDisplay.setPadding(
                resources.getDimensionPixelSize(R.dimen.wh_16),
                resources.getDimensionPixelSize(R.dimen.wh_16),
                resources.getDimensionPixelSize(R.dimen.wh_16),
                0
            )
        }

    }

    override fun getItemCount(): Int {
        return if (chapters.isNullOrEmpty()) 0 else chapters.size
    }
}