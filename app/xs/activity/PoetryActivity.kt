package com.sky.xs.activity

import android.content.Context
import android.content.Intent
import android.graphics.RectF
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.common.LogUtils
import com.sky.common.ScreenUtils
import com.sky.notes.pop.CatalogPop
import com.sky.uidesign.activity.MVVMActivity
import com.sky.xs.R
import com.sky.xs.adapter.PoetryAdapter
import com.sky.xs.databinding.ActivityPoetryBinding
import com.sky.xs.entity.ChapterEntity
import com.sky.xs.entity.PoetryEntity
import com.sky.xs.vm.PoetryViewModel

/**
 * Created by libin on 2020/05/13 2:33 PM Wednesday.
 */
class PoetryActivity : MVVMActivity<ActivityPoetryBinding, PoetryViewModel>(), PoetryViewModel.IPoetryView {
    lateinit var adapter: PoetryAdapter
    lateinit var poetry: PoetryEntity

    companion object {
        const val KEY = "poetry"

        fun newInstance(context: Context, poetry: PoetryEntity) {
            val intent = Intent(context, PoetryActivity::class.java)
            intent.putExtra(KEY, poetry)
            context.startActivity(intent)
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_poetry

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getViewModel(): PoetryViewModel {
        return PoetryViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding!!.recycler.layoutManager = LinearLayoutManager(this)
        adapter = PoetryAdapter()
        viewDataBinding!!.recycler.adapter = adapter

        poetry = intent.getSerializableExtra(KEY) as PoetryEntity

        viewModel?.getChapter(poetry.path)


        setToolbar(poetry.name, viewDataBinding!!.actionBar.toolbar)
        viewDataBinding?.poetry = this
    }

    override fun loadChapter(chapterEntities: MutableList<ChapterEntity>) {
        adapter.chapters = chapterEntities
        adapter.notifyDataSetChanged()
        LogUtils.i(chapterEntities.toString())
    }

    private var downX = 0f
    private var downY = 0f
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev!!.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
            }
            MotionEvent.ACTION_UP -> {
                val width = ScreenUtils.getWidthPX(this) / 3f
                val height = ScreenUtils.getHeightPX(this) / 3f
                val rect = RectF(width, height, width * 2, height * 2)
                if (Math.abs(ev.x - downX) < 5 && Math.abs(ev.y - downY) < 1 && rect.contains(
                        ev.x,
                        ev.y
                    )
                )
                    viewDataBinding!!.llBottomBar.visibility =
                        if (viewDataBinding!!.llBottomBar.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun nextChapter(v: View) {
        val layoutManager = viewDataBinding!!.recycler.layoutManager as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        layoutManager.scrollToPositionWithOffset(position + 1, 0)
        layoutManager.stackFromEnd = true
    }

    fun upToChapter(v: View) {
        val layoutManager = viewDataBinding!!.recycler.layoutManager as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        viewDataBinding!!.recycler.scrollToPosition(position - 1)
    }

    fun moveToChapter(position: Int) {
        val layoutManager = viewDataBinding!!.recycler.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(position, 0)
        layoutManager.stackFromEnd = true
    }

    fun showCatalogPop(v: View) {
        val floderPop = CatalogPop(
            LayoutInflater.from(this).inflate(R.layout.include_recycler, null),
            ScreenUtils.getWidthPX(this), (ScreenUtils.getHeightPX(this) * 0.7).toInt()
        )
        floderPop?.datas = adapter?.chapters
        floderPop?.setOnItemClickListener { _, position -> moveToChapter(position) }
        floderPop?.showAtLocation(window.decorView, Gravity.CENTER, 0, 0)
    }
}