package com.denizd.imfine.viewmodel

class SettingsViewModel : BaseViewModel() {

    fun getUsername(): String = repo.getUsername()
    fun setUsername(newUsername: String) { repo.setUsername(newUsername) }
}