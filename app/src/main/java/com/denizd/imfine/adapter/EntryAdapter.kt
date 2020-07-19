package com.denizd.imfine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denizd.imfine.R
import com.denizd.imfine.databinding.ItemEntryBinding
import com.denizd.imfine.model.Entry
import com.denizd.imfine.util.toDate

class EntryAdapter(
    private var items: List<Entry> = listOf(),
) : RecyclerView.Adapter<EntryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemEntryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].also { currentItem ->
            holder.binding.apply {
                emotion.text =
                    emotion.context.resources.getStringArray(R.array.emotions)[currentItem.rating]
                firstLine.text = currentItem.time.toDate()
                secondLine.text = currentItem.description
                secondLine.isSelected = true
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<Entry>) {
        this.items = items
    }
}