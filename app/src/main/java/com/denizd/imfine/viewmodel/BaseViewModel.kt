package com.denizd.imfine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denizd.imfine.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class BaseViewModel : ViewModel() {

    protected val repo: Repository = Repository.get()

    protected fun doAsync(action: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) { action() }
    }

    protected fun <T> returnBlocking(action: () -> T) = runBlocking(Dispatchers.IO) { action() }
}