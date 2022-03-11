package com.sky.xs.vm

import com.sky.common.FileUtils
import com.sky.uidesign.BaseApplication
import com.sky.uidesign.viewmodel.MVVMBaseViewModel
import com.sky.xs.vm.PoetryViewModel.IPoetryView
import com.sky.xs.utils.PoetryAsync
import com.sky.xs.App
import com.sky.xs.entity.ChapterEntity
import com.sky.uidesign.api.IMVVMView

/**
 * Created by libin on 2020/05/13 2:43 PM Wednesday.
 */
class PoetryViewModel : MVVMBaseViewModel<IPoetryView?>() {
    fun getChapter(fileName: String?) {
        val async = PoetryAsync()
        async.execute(FileUtils.readAssestToChar(BaseApplication.app, fileName))
        async.chapterList = { chapterEntities: MutableList<ChapterEntity> ->
            if (view != null) {
                view!!.loadChapter(chapterEntities)
            }
            null
        }
    }

    interface IPoetryView : IMVVMView {
        fun loadChapter(chapterEntities: MutableList<ChapterEntity>)
    }
}