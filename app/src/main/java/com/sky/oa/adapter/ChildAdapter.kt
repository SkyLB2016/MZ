package com.sky.oa.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.oa.R
import com.sky.oa.activity.PoetryActivity
import com.sky.oa.databinding.AdapterNoteitemBinding
import com.sky.oa.entity.PoetryEntity
import com.sky.ui.adapter.MvvmHolder

class ChildAdapter : RecyclerView.Adapter<MvvmHolder>() {
    private val fontIcon = intArrayOf(
        R.string.font,
        R.string.font01,
        R.string.font02,
        R.string.font03,
        R.string.font04,
        R.string.font05,
        R.string.font06,
        R.string.font07,
        R.string.font08
    )
    private lateinit var viewBinding: AdapterNoteitemBinding
    private lateinit var context: Context

    var poetries: MutableList<PoetryEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvvmHolder {
        context = parent.context
        viewBinding = AdapterNoteitemBinding.inflate(LayoutInflater.from(context))
//        return MvvmViewHolder(viewBinding.root)
        return MvvmHolder(viewBinding.root)
    }

    override fun onBindViewHolder(holder: MvvmHolder, position: Int) {
        viewBinding.tvName.text = poetries[position]?.name
        val face = Typeface.createFromAsset(
            context.assets,
            "font/icomoon.ttf"
        ) //字体，icomoon对应fonticon

        viewBinding.tvImage.text = context.resources.getString(fontIcon[position % 9])
        viewBinding.tvImage.typeface = face
        viewBinding.tvImage.textSize = 50f
        viewBinding.cardView.setOnClickListener {
            PoetryActivity.newInstance(context, poetries[position])
        }
    }

    override fun getItemCount() = poetries?.size ?: 0
}
