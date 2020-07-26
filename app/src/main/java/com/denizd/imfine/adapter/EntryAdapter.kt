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
    private val onLongClick: (id: Long, time: Long) -> Unit,
) : RecyclerView.Adapter<EntryAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemEntryBinding,
        private val onLongClick: (id: Long, time: Long) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        var id: Long = -1L
        var time: Long = -1L
        init {
            itemView.setOnLongClickListener {
                onLongClick(id, time)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemEntryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false,
        ), onLongClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].also { currentItem ->
            holder.id = currentItem.id
            holder.time = currentItem.time
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
        notifyDataSetChanged()
    }
}