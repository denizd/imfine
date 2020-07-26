package com.denizd.imfine.viewmodel

import androidx.lifecycle.LiveData
import com.denizd.imfine.model.Entry

class HomeViewModel : BaseViewModel() {

    val latestEntries: LiveData<List<Entry>> = repo.latestEntries

    fun getUsername(): String = repo.getUsername()
}