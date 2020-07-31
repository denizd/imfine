package com.denizd.imfine.util

import android.content.Context
import android.view.LayoutInflater
import com.denizd.imfine.R
import com.denizd.imfine.databinding.DialogEntryBinding
import com.denizd.imfine.model.Entry
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun createEntryDialog(context: Context,
                      onCreateEntry: (entry: Entry) -> Unit) {

    MaterialAlertDialogBuilder(context).also { dialogBuilder ->
        DialogEntryBinding.inflate(LayoutInflater.from(context)).apply {
            val dialog = dialogBuilder.setCancelable(false).setView(root).create()
            val emotions = context.resources.getStringArray(R.array.emotions)
            slider.addOnChangeListener { _, value, _ ->
                currentEmotion.text = emotions[value.toInt()]
            }
            descriptionLayout.hint =
                context.resources.getStringArray(R.array.entry_description_encouragements).random()
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            createButton.setOnClickListener {
                if (dateBack.text.isNullOrBlank()) {
                    onCreateEntry(Entry(slider.value.toInt(), description.text.toString()))
                    dialog.dismiss()
                } else try {
                    onCreateEntry(Entry(
                        slider.value.toInt(),
                        description.text.toString(),
                        time = dateBack.text.toString().toTimestamp(),
                    ))
                    dialog.dismiss()
                } catch (e: IllegalArgumentException) {
                    dateBack.error = context.getString(R.string.invalid_date)
                }
            }
            dialog.show()
        }
    }
}