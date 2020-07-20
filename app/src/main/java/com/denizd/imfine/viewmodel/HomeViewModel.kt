package com.denizd.imfine.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.denizd.imfine.model.Entry

class HomeViewModel : BaseViewModel() {

    val latestEntries: LiveData<List<Entry>> = repo.latestEntries

    fun getUsername(): String = repo.getUsername()
}