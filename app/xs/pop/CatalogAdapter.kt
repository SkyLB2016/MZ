package com.sky.xs.pop

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.uidesign.adapter.MvvmViewHolder
import com.sky.xs.R
import com.sky.xs.databinding.ItemTvBinding
import com.sky.xs.entity.ChapterEntity
import kotlinx.android.synthetic.main.item_tv.view.*

/**
 * Created by libin on 2020/05/14 2:49 PM Thursday.
 */
class CatalogAdapter : RecyclerView.Adapter<MvvmViewHolder>() {
    var datas: MutableList<ChapterEntity> = ArrayList()

    var itemClick: ((View, Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvvmViewHolder {

        return MvvmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false)
        )
//        return MvvmViewHolder(
//            ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
//        )
    }

    override fun getItemCount(): Int {
        return if (datas == null) 0 else datas.size
    }

    override fun onBindViewHolder(holder: MvvmViewHolder, position: Int) {
        with(holder!!.itemView) {
            tv.text = datas[position].chapter
            tv.setBackgroundResource(R.drawable.shape_ffc107)
            tv.textSize = 18f
            tv.gravity = Gravity.LEFT
        }
        holder.itemView.setOnClickListener {
            itemClick?.invoke(holder.itemView, position)
        }
    }
}