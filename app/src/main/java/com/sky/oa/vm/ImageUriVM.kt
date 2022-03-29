package com.sky.oa.vm

import android.annotation.SuppressLint
import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import com.sky.common.utils.LogUtils
import com.sky.common.utils.SDCardUtils
import com.sky.oa.entity.ImageFloder
import com.sky.ui.viewmodel.BaseVM
import java.io.File
import java.io.FilenameFilter
import java.util.ArrayList
import java.util.HashSet

class ImageUriVM : BaseVM() {
    val liveDataFloders = MutableLiveData<MutableList<ImageFloder>>()//照片所在的文件夹列表
    val liveDataParent = MutableLiveData<File>()

    @SuppressLint("Range")
    fun checkDiskImage(context: Context) {
        val parentPaths = HashSet<String>()//临时的辅助类，用于防止同一个文件夹的多次扫描

        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            "${MediaStore.Images.Media.MIME_TYPE}=? or ${MediaStore.Images.Media.MIME_TYPE}=?",
            arrayOf("image/jpeg", "image/png"),
            MediaStore.Images.Media.DATE_MODIFIED
        )
        val floders = ArrayList<ImageFloder>()
//        var totalCount = 0//图片总数
        var maxCount = 0//图片数量最多的文件
        var parent: File? = null//所要显示的文件夹
        while (cursor!!.moveToNext()) {
            val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            val parentFile = File(path).parentFile ?: continue//parent如果为空跳出此次循环
            val parentPath = parentFile.absolutePath//获取父文件夹的路径
            if (parentPaths.contains(parentPath)) continue//判断此文件夹是否已经检测添加，如已添加，结束此次循环

            parentPaths.add(parentPath)//加入检测列表里

            val floder = ImageFloder()//开始收集父级文件夹得信息
            floder.dirPath = parentPath//父文件夹路径
            floder.firstImagePath = path//第一张图片
            val count = parentFile?.list(filter)?.size ?: 0//总数量
            if (count == 0) continue
            floder.count = count
            floders.add(floder)

//            totalCount += count//图片的总数量
            //选出图片数量最多的文件夹floder，并显示
            if (count > maxCount) {
                maxCount = count
                parent = parentFile
            }
        }
        cursor.close()
        liveDataFloders.value = floders
        liveDataParent.value = parent
        LogUtils.i(floders.toString())
        LogUtils.i(parent?.absolutePath.toString())
    }

    private var filter: FilenameFilter = FilenameFilter { dir, filename ->
        val type = arrayOf(".jpg", ".JPG", ".jpeg", ".JPEG", ".png", ".PNG")
        type.any { filename.endsWith(it) }
    }
}
