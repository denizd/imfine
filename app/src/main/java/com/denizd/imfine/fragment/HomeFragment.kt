package com.denizd.imfine.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.denizd.imfine.R
import com.denizd.imfine.databinding.FragmentHomeBinding
import com.denizd.imfine.model.Entry
import com.denizd.imfine.util.viewBinding
import com.denizd.imfine.viewmodel.HomeViewModel
import kotlin.math.roundToInt
import kotlin.random.Random

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override val titleId: Int = R.string.home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.latestEntries.observe(viewLifecycleOwner) { latestEntries ->
            if (latestEntries.isEmpty()) {
                binding.noEntryScreen.visibility = View.VISIBLE
            } else {
                updateUi(latestEntries)
            }
        }
    }

    private fun updateUi(entries: List<Entry>) {
        binding.apply {
            title.text = String.format(
                resources.getStringArray(R.array.greetings).random(),
                viewModel.getUsername()
            )
            val average = entries.map { it.rating.toFloat() }.average().roundToInt()
            ratingAverageTagline.text = getRandomRatingArray()[average]
        }
    }

    private fun getRandomRatingArray(): Array<out String> =
        resources.getStringArray(when (Random.nextInt(2)) {
            0 -> R.array.average_rating_0
            else -> R.array.average_rating_1
        })
}