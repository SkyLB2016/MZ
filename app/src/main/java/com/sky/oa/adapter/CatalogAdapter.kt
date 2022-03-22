package com.sky.oa.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.oa.R
import com.sky.oa.databinding.ItemTvBinding
import com.sky.oa.entity.ChapterEntity
import com.sky.ui.adapter.MvvmHolder

/**
 * Created by libin on 2020/05/14 2:49 PM Thursday.
 */
class CatalogAdapter : RecyclerView.Adapter<MvvmHolder<ItemTvBinding>>() {
    var datas: MutableList<ChapterEntity> = ArrayList()
    var itemClick: ((View, Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvvmHolder<ItemTvBinding> {
        return MvvmHolder(ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return if (datas == null) 0 else datas.size
    }

    override fun onBindViewHolder(holder: MvvmHolder<ItemTvBinding>, position: Int) {
        holder.binding.tv.text = datas[position].chapter
        holder.binding.tv.setBackgroundResource(R.drawable.shape_ffc107)
        holder.binding.tv.textSize = 18f
        holder.binding.tv.gravity = Gravity.LEFT
        holder.itemView.setOnClickListener {
            itemClick?.invoke(holder.itemView, position)
        }
    }
}