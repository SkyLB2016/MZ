package com.sky.xs.pop

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
class CatalogAdapter : RecyclerView.Adapter<MvvmHolder>() {
    var datas: MutableList<ChapterEntity> = ArrayList()
    lateinit var binding: ItemTvBinding

    var itemClick: ((View, Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvvmHolder {

//        return MvvmViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false)
//        )
        binding = ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MvvmHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return if (datas == null) 0 else datas.size
    }

    override fun onBindViewHolder(holder: MvvmHolder, position: Int) {
        binding.tv.text = datas[position].chapter
        binding.tv.setBackgroundResource(R.drawable.shape_ffc107)
        binding.tv.textSize = 18f
        binding.tv.gravity = Gravity.LEFT
        holder.itemView.setOnClickListener {
            itemClick?.invoke(holder.itemView, position)
        }
    }
}