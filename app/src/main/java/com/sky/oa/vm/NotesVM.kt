package com.sky.oa.vm

import android.content.res.AssetManager
import androidx.lifecycle.Transformations
import com.sky.oa.repository.NotesRepository
import com.sky.ui.viewmodel.BaseVM

class NotesVM(val repository: NotesRepository) : BaseVM() {
    val datas = Transformations.map(repository.notes) { it }
    fun getPoetries(assets: AssetManager, directory: String) {
        repository.getPoetries(assets, directory)
    }
}
