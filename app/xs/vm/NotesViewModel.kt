package com.sky.xs.vm

import android.content.res.AssetManager
import com.sky.common.FileUtils
import com.sky.common.LogUtils
import com.sky.uidesign.api.IMVVMView
import com.sky.uidesign.viewmodel.MVVMBaseViewModel
import com.sky.xs.entity.PoetryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.Collator
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

class NotesViewModel : MVVMBaseViewModel<NotesViewModel.INotesView>() {

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
            try {
                files = assets.list(dir)
            } catch (e: IOException) {

            }
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


        GlobalScope.launch(Dispatchers.Main) {
            view?.loadPoetry(maps)
        }
    }

    interface INotesView : IMVVMView {
        fun loadPoetry(data: LinkedHashMap<String, ArrayList<PoetryEntity>>)
    }
}
