package com.sky.oa.activity

import android.content.ClipboardManager
import android.graphics.RectF
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.common.utils.FileUtils
import com.sky.common.utils.LogUtils
import com.sky.common.utils.ScreenUtils
import com.sky.oa.R
import com.sky.oa.adapter.ArticleAdapter
import com.sky.oa.databinding.ActivitySlidingBinding
import com.sky.oa.entity.ChapterEntity
import com.sky.oa.entity.PoetryEntity
import com.sky.oa.pop.CatalogPop
import com.sky.oa.repository.NotesRepository
import com.sky.oa.vm.ArtivleVM
import com.sky.ui.activity.BaseActivity
import com.sky.ui.activity.MVVMActivity
import java.text.Collator
import java.util.*
import androidx.lifecycle.Observer

/**
 * Created by SKY on 2018/3/16.
 */
class SlidingActivity : MVVMActivity<ActivitySlidingBinding, ArtivleVM>() {
    private var gravity = Gravity.LEFT

    //    lateinit var poetry: PoetryEntity
    lateinit var adapter: ArticleAdapter

    private val poetries = ArrayList<PoetryEntity>();
    private lateinit var clipM: ClipboardManager

    override fun getBinding() = ActivitySlidingBinding.inflate(layoutInflater)
    override fun getViewModel() =
//        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArtivleVM::class.java)
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ArtivleVM(NotesRepository()) as T
            }
        }).get(ArtivleVM::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.appBar.toolbar.setNavigationIcon(R.mipmap.ic_menu)
        binding.appBar.toolbar.setNavigationOnClickListener {
            binding.sliding.toggleMenu()
        }
        binding.appBar.tvRight.text = "3.16建"

        adapter = ArticleAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recycler.adapter = adapter

        clipM = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        initEvent()
        addAni()
        loadData()
        binding.sliding.menuState = { state ->
            LogUtils.i("state==$state")
        }
    }

    private fun addAni() {
        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.anim_layout)
        controller.delay = 0.5f
        controller.order = LayoutAnimationController.ORDER_NORMAL
        binding.recycler.layoutAnimation = controller
    }

    private fun loadData() {
        var dir = "Documents"//assets初始路径
        var array: Array<String>? = null//assets取出的目录名称
        val link = LinkedList<String>()
        link.add(dir)
//            val stack = Stack<String>();
//            stack.add(dir)
        var item = PoetryEntity()
        //取出Documents下的所有文本文件
        while (link.isNotEmpty()) {
            dir = link.removeFirst()
            array = assets.list(dir)
            for (i in array!!) {
                if (i.endsWith(".txt")) {
                    item = item.copy()
//                    item = PoetryEntity()
                    item.name = i.substring(0, i.length - 4)
                    item.path = "$dir/$i"
                    poetries.add(item)
                } else {
                    if (i != "other") {
                        link.add("$dir/$i")
                    }
                }
            }
        }
        val collator = Collator.getInstance(Locale.CHINA)
        poetries.sortWith(Comparator { o1, o2 -> collator.compare(o1.path, o2.path) })

        var tv: TextView
        for ((index, poetry) in poetries.withIndex()) {
            tv = LayoutInflater.from(this).inflate(R.layout.item_tv, binding.flow, false) as TextView
//            tv.width = resources.getDimensionPixelSize(R.dimen.wh_96)
            tv.minWidth = resources.getDimensionPixelSize(R.dimen.wh_48)
            tv.textSize = 18f
            tv.maxLines = 1
//            tv.text = text.substringAfterLast("/", ".").substringBefore(".", "")
            tv.text = poetry.name
            tv.setPadding(20, 6, 20, 6)
            tv.id = index
            binding.flow.addView(tv)
            tv.setOnClickListener(selectArticle)
        }
        val text = getDocument("Documents/笔记/享学课堂.txt")
        binding.appBar.tvCenter.text = text.lines()[0]

        viewModel.articleDetail.observe(this, Observer {
            adapter.datas = it
            adapter.notifyDataSetChanged()
//            LogUtils.i(it.toString())
        })
        viewModel?.getChapter("Documents/笔记/享学课堂.txt")
    }

    private fun getDocument(sign: String): String {
        return FileUtils.readAssestToChar(this, sign)
    }

    private fun initEvent() {
        binding.tvLast.setOnClickListener { upToChapter() }
        binding.tvCatalog.setOnClickListener { showCatalogPop(adapter?.datas) }
        binding.tvNext.setOnClickListener { nextChapter() }
    }

    private val selectArticle = View.OnClickListener { v ->
        //        gravity = when (v?.id) {
//            in 2..5 -> Gravity.CENTER
//            else -> Gravity.LEFT
//        }
//        val text = getDocument(poetries[v!!.id].path)
//        clipM.primaryClip = ClipData.newPlainText("",text.lines()[0])
        binding.appBar.tvCenter.text = poetries[v!!.id].name

        viewModel?.getChapter(poetries[v!!.id].path)
//        LogUtils.i("行数==${text.lines().size}")
    }

    private fun nextChapter() {
        val layoutManager = binding.recycler.layoutManager as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        layoutManager.scrollToPositionWithOffset(position + 1, 0)
        layoutManager.stackFromEnd = true
    }

    private fun upToChapter() {
        val layoutManager = binding.recycler.layoutManager as LinearLayoutManager
        val position = layoutManager.findFirstVisibleItemPosition()
        binding.recycler.scrollToPosition(position - 1)
    }

    private fun moveToChapter(position: Int) {
        val layoutManager = binding.recycler.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(position, 0)
        layoutManager.stackFromEnd = true
    }

    private fun showCatalogPop(floders: List<ChapterEntity>?) {
        val floderPop = CatalogPop(
            LayoutInflater.from(this).inflate(R.layout.include_recycler, null),
            ScreenUtils.getWidthPX(this), (ScreenUtils.getHeightPX(this) * 0.7).toInt()
        )
        floderPop?.datas = floders
        floderPop?.setOnItemClickListener { _, position -> moveToChapter(position) }
        floderPop?.showAtLocation(window.decorView, Gravity.CENTER, 0, 0)
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
                    ) && binding.sliding.isClose
                )
                    binding.llBottomBar.visibility =
                        if (binding.llBottomBar.visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        when {
            binding.sliding.isOpen -> binding.sliding.toggleMenu()
            binding.llBottomBar.visibility == View.VISIBLE -> binding.llBottomBar.visibility = View.GONE
            else -> super.onBackPressed()
        }
    }
}