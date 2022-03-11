package com.sky.notes.pop

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sky.uidesign.widget.BasePop
import com.sky.xs.R
import com.sky.xs.entity.ChapterEntity
import com.sky.xs.pop.CatalogAdapter

/**
 * Created by SKY on 2016/1/11 13:14.
 * 文件夹pop
 */
class CatalogPop(view: View, width: Int, height: Int) : BasePop<ChapterEntity>(view, width, height) {
    private var recycle: RecyclerView? = null

    private lateinit var adapter: CatalogAdapter

    override fun initView() {
        super.initView()
        var swipe: SwipeRefreshLayout = view.findViewById(R.id.swipe)
        swipe.isEnabled = false
        recycle = view.findViewById(R.id.recycler)
        recycle?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapter = CatalogAdapter()
        recycle?.adapter = adapter
    }

    override fun initEvent() {
        adapter?.itemClick ={ view, position ->
            itemClickListener?.onItemClick(view, position)
            dismiss()
        }
    }

    override fun initDatas() {
        adapter?.datas = popDatas
    }
}
