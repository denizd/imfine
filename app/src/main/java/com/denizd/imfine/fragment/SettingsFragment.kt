package com.denizd.imfine.fragment

import androidx.fragment.app.viewModels
import com.denizd.imfine.R
import com.denizd.imfine.databinding.FragmentSettingsBinding
import com.denizd.imfine.util.viewBinding
import com.denizd.imfine.viewmodel.SettingsViewModel

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    override val titleId: Int = R.string.settings
    override val viewModel: SettingsViewModel by viewModels()
}