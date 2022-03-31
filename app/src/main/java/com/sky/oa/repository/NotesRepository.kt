package com.sky.oa.repository

import android.content.res.AssetManager
import androidx.lifecycle.MutableLiveData
import com.sky.common.utils.FileUtils
import com.sky.oa.entity.ChapterEntity
import com.sky.oa.entity.PoetryEntity
import com.sky.oa.utils.PoetryAsync
import com.sky.ui.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/3/14 11:07 上午
 * @Version: 1.0
 */
class NotesRepository {
    val notes = MutableLiveData<LinkedHashMap<String, ArrayList<PoetryEntity>>>()
    val articleDetails = MutableLiveData<MutableList<ChapterEntity>>()

    fun getPoetries(assets: AssetManager, directory: String) {
        val maps: LinkedHashMap<String, ArrayList<PoetryEntity>> = linkedMapOf()
        val dirs: LinkedList<String> = LinkedList()
        dirs.add(directory)

        var dir: String
        var files: Array<String>? = null//文件夹中包含的数组
        var poetry: PoetryEntity
        var poetries: ArrayList<PoetryEntity>? = arrayListOf()
        while (dirs.isNotEmpty()) {
            dir = dirs.removeFirst()
            files = assets.list(dir)
            files?.forEach { fileName ->
                if (fileName.endsWith(".txt")) {
                    poetry = PoetryEntity(
                        fileName.substring(0, fileName.length - 4),
                        path = "$dir/$fileName",
                        parentDir = dir,
                        total = FileUtils.readCharFile(assets.open("$dir/$fileName")).length
                    )
                    poetries = maps[dir]
                    if (poetries == null) {
                        poetries = arrayListOf()
                        maps[dir] = poetries!!
                    }
                    poetries?.add(poetry)
                } else {
                    dirs.addLast("$dir/$fileName")
                }
            }
            poetries?.sortWith { o1, o2 ->
                o2?.total?.compareTo(o1!!.total)!!//降序排列
            }
        }

//        notes.postValue(maps)
        //或者用这种
        GlobalScope.launch(Dispatchers.Main) {
            notes.value = maps
        }
    }

    fun getChapter(fileName: String) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val async = PoetryAsync()
                val chapterList = async.doInBackground(FileUtils.readAssestToChar(BaseApplication.app, fileName))
//                articleDetails.value = chapterList
                articleDetails.postValue(chapterList)

            }
        }

//        val async = PoetryAsync()
//        async.execute(FileUtils.readAssestToChar(BaseApplication.app, fileName))
//        async.chapterList = { chapterEntities: MutableList<ChapterEntity> ->
//            articleDetails.value = chapterEntities
//        }
    }

}