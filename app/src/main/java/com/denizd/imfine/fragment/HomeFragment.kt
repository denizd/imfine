package com.denizd.imfine.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.denizd.imfine.R
import com.denizd.imfine.databinding.FragmentHomeBinding
import com.denizd.imfine.util.viewBinding
import com.denizd.imfine.viewmodel.HomeViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override val titleId: Int = R.string.home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.latestEntries.observe(viewLifecycleOwner) { latestEntries ->

        }
    }
}