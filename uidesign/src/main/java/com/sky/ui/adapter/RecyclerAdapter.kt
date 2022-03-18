package com.sky.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.sky.ui.adapter.RecyclerAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import androidx.viewbinding.ViewBinding

/**
 * Created by SKY on 16/5/10 下午3:50.
 * RecyclerView的万能适配器
 */
abstract class RecyclerAdapter<V, T> : RecyclerView.Adapter<MvvmHolder>() where V : ViewBinding {
    var context: Context? = null
    lateinit var binding: V

    //    var binding: V? = null
    var datas: MutableList<T>? = null
        set(value) {
            field = value;
            notifyDataSetChanged()
        }


    //viewItem的点击事件监听
    //子view的按钮监听
    var onItemClickListener: ((View, Int) -> Unit)? = null

    //长按监听
    var itemLongClickListener: ((View, Int) -> Boolean)? = null

    fun addDatas(datas: List<T>?) {
        this.datas!!.addAll(datas!!)
        notifyDataSetChanged()
    }

    fun clearDatas() {
        if (datas == null) return
        datas = null
        notifyDataSetChanged()
    }

    /**
     * @param obj      添加item
     * @param position -1时最后一个添加
     */
    //添加item,默认从最后添加
    @JvmOverloads
    fun addItem(obj: T, position: Int = LASTITEM) {
        var position = position
        position = if (position == LASTITEM) itemCount else position
        datas!!.add(position, obj)
        notifyItemInserted(position)
    }

    /**
     * @param position,为-1时，删除最后一个
     */
    fun deleteItem(position: Int) {
        var position = position
        if (position == LASTITEM && itemCount > 0) position = itemCount - 1
        if (position in (LASTITEM + 1) until itemCount) {
            datas!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvvmHolder {
        context = parent.context
        binding = getBinding(context, parent)
        return MvvmHolder(binding!!.root)
    }

    abstract fun getBinding(context: Context?, parent: ViewGroup): V


    override fun onBindViewHolder(holder: MvvmHolder, position: Int) {
        onAchieveHolder(holder, position)
        holder.itemView.setOnLongClickListener {
            return@setOnLongClickListener itemLongClickListener?.invoke(it, holder.layoutPosition)
                ?: false
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(holder.itemView, holder.layoutPosition)
        }
    }

    /**
     * 加载数据内容于view上
     *
     * @param holder   holder
     * @param position 位置
     */
    protected abstract fun onAchieveHolder(holder: MvvmHolder?, position: Int)

    companion object {
        protected const val LASTITEM = -1
    }
}