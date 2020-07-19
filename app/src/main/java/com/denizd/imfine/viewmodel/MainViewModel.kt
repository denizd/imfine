package com.denizd.imfine.viewmodel

import android.content.Context
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denizd.imfine.R
import com.denizd.imfine.data.Repository
import com.denizd.imfine.fragment.HistoryFragment
import com.denizd.imfine.fragment.HomeFragment
import com.denizd.imfine.fragment.SettingsFragment
import com.denizd.imfine.model.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel : ViewModel() {

    private lateinit var repo: Repository

    fun init(applicationContext: Context) {
        repo = Repository.get(applicationContext)
    }

    fun checkHourlyEntry(): Boolean = runBlocking(Dispatchers.IO) { repo.checkHourlyEntry() }
    fun hasCreatedHourlyEntry(): Boolean = repo.hasCreatedHourlyEntry()

    fun save(entry: Entry) {
        viewModelScope.launch(Dispatchers.IO) { repo.save(entry) }
    }

    fun fragmentNameFor(@IdRes id: Int): String = when (id) {
        R.id.home -> HomeFragment::class
        R.id.history -> HistoryFragment::class
        R.id.settings -> SettingsFragment::class
        else -> throw IllegalArgumentException("Requested fragment does not exist")
    }.java.simpleName
}