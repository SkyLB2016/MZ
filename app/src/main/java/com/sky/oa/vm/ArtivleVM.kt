package com.sky.oa.vm

import androidx.lifecycle.Transformations
import com.sky.oa.repository.NotesRepository
import com.sky.ui.viewmodel.BaseVM


/**
 * Created by libin on 2020/05/13 2:43 PM Wednesday.
 */
class ArtivleVM(private val repository: NotesRepository) : BaseVM() {

    val articleDetail = Transformations.map(repository.articleDetails) { it }

    fun getChapter(fileName: String) {
        repository.getChapter(fileName)
    }

}