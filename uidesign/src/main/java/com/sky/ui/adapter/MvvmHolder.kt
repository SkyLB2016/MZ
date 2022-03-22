package com.sky.ui.adapter

import androidx.viewbinding.ViewBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by libin on 2020/05/14 3:24 PM Thursday.
 */
class MvvmHolder<V : ViewBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root)