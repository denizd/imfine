package com.denizd.imfine.util

import java.text.SimpleDateFormat
import java.util.*

private val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd, HH:mm", Locale.ROOT)
private val sdfDay: SimpleDateFormat = SimpleDateFormat("dd", Locale.ROOT)

fun Long.toDate(): String = sdf.format(Date(this))

fun Date.toDate(): String = sdfDay.format(this)

fun String.toTimestamp(): Long =
    (sdf.parse(this) ?: throw IllegalArgumentException("Invalid date")).time