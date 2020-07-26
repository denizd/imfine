package com.denizd.imfine.viewmodel

import androidx.lifecycle.LiveData
import com.denizd.imfine.model.Entry

class HistoryViewModel : BaseViewModel() {

    val allEntries: LiveData<List<Entry>> = repo.allEntries

    fun deleteEntry(id: Long) = doAsync { repo.deleteEntry(id) }
}