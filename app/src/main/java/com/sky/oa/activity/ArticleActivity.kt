package com.sky.oa.activity

import android.content.Context
import android.content.Intent
import android.graphics.RectF
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.common.utils.ScreenUtils
import com.sky.oa.pop.CatalogPop
import com.sky.oa.R
import com.sky.oa.databinding.ActivityPoetryBinding
import com.sky.oa.entity.PoetryEntity
import com.sky.oa.repository.NotesRepository
import com.sky.oa.vm.ArtivleVM
import com.sky.ui.activity.MVVMActivity
import com.sky.oa.adapter.ArticleAdapter

/**
 * Created by libin on 2020/05/13 2:33 PM Wednesday.
 */
class ArticleActivity : MVVMActivity<ActivityPoetryBinding, ArtivleVM>() {
    lateinit var adapter: ArticleAdapter
    companion object {
        const val KEY = "poetry"

        fun newInstance(context: Context, poetry: PoetryEntity) {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(KEY, poetry)
            context.startActivity(intent)
        }
    }

    override fun getBinding() = ActivityPoetryBinding.inflate(layoutInflater)
    override fun getViewModel() =
//        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArtivleVM::class.java)
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ArtivleVM(NotesRepository()) as T
            }
        }).get(ArtivleVM::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val poetry = intent.getSerializableExtra(KEY) as PoetryEntity
        setToolbar(binding!!.appBar.toolbar, poetry.name)

        adapter = ArticleAdapter()
        binding?.recycler?.layoutManager = LinearLayoutManager(this)
        binding?.recycler?.adapter = adapter
        viewModel?.getChapter(poetry.path)

        viewModel.articleDetail.observe(this, Observer {
            adapter.datas = it
            adapter.notifyDataSetChanged()
//            LogUtils.i(it.toString())
        })
        binding.tvPrevious.setOnClickListener { previousChapter(it) }
        binding.tvCatalog.setOnClickListener { showCatalogPop(it) }
        binding.tvNext.setOnClickListener { nextChapter(it) }
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
                    binding!!.llBottomBar.visibility =
                        if (binding!!.llBottomBar.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun previousChapter(v: View) {
        val layoutManager = binding!!.recycler.layoutManager as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        binding!!.recycler.scrollToPosition(position - 1)
    }

    private fun nextChapter(v: View) {
        val layoutManager = binding!!.recycler.layoutManager as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        layoutManager.scrollToPositionWithOffset(position + 1, 0)
        layoutManager.stackFromEnd = true
    }

    private fun showCatalogPop(v: View) {
        val floderPop = CatalogPop(
            LayoutInflater.from(this).inflate(R.layout.include_recycler, null),
            ScreenUtils.getWidthPX(this), (ScreenUtils.getHeightPX(this) * 0.7).toInt()
        )
        floderPop?.datas = adapter?.datas!!
        floderPop?.setOnItemClickListener { _, position -> moveToChapter(position) }
        floderPop?.showAtLocation(window.decorView, Gravity.CENTER, 0, 0)
    }

    private fun moveToChapter(position: Int) {
        val layoutManager = binding!!.recycler.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(position, 0)
        layoutManager.stackFromEnd = true
    }
}