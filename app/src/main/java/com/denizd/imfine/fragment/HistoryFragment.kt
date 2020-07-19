package com.denizd.imfine.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.denizd.imfine.R
import com.denizd.imfine.adapter.EntryAdapter
import com.denizd.imfine.databinding.FragmentHistoryBinding
import com.denizd.imfine.util.viewBinding
import com.denizd.imfine.viewmodel.HistoryViewModel

class HistoryFragment : BaseFragment(R.layout.fragment_history) {

    override val binding: FragmentHistoryBinding by viewBinding(FragmentHistoryBinding::bind)
    override val titleId: Int = R.string.history
    override val viewModel: HistoryViewModel by viewModels()

    private val entryAdapter: EntryAdapter = EntryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allEntries.observe(viewLifecycleOwner) { newEntries ->
            entryAdapter.setItems(newEntries)
            binding.recyclerView.scheduleLayoutAnimation()
        }

        binding.recyclerView.apply {
            adapter = entryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}