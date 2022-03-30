package com.sky.oa.vm

import android.content.Context
import androidx.lifecycle.Transformations
import com.sky.oa.repository.ImageRepository
import com.sky.ui.viewmodel.BaseVM

class ImageUriVM(val repository: ImageRepository) : BaseVM() {
    val liveDataFloders = Transformations.map(repository.liveDataFloders) { it }//照片所在的文件夹列表
    val liveDataParent = Transformations.map(repository.liveDataParent) { it }
    val liveDataUrl = Transformations.map(repository.liveDataUrl) { it }

    fun checkDiskImage(context: Context) {
        repository.checkDiskImage(context)
    }

    fun getImageUrl() {
        repository.getImageUrl()
    }

}
