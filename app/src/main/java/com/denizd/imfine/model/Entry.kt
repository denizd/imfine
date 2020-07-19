package com.denizd.imfine.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry(
    val rating: Int,
    val description: String = "",
    val time: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)