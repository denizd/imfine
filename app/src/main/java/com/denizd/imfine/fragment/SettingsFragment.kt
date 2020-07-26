package com.denizd.imfine.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.denizd.imfine.R
import com.denizd.imfine.databinding.FragmentSettingsBinding
import com.denizd.imfine.util.viewBinding
import com.denizd.imfine.viewmodel.SettingsViewModel

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    override val titleId: Int = R.string.settings
    override val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fieldUsername.apply {
            setText(viewModel.getUsername())
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    viewModel.setUsername(s.toString())
                }
            })
        }
    }
}