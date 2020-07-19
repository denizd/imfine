package com.denizd.imfine.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.denizd.imfine.R
import com.denizd.imfine.databinding.ActivityMainBinding
import com.denizd.imfine.fragment.*
import com.denizd.imfine.util.createEntryDialog
import com.denizd.imfine.util.viewBinding
import com.denizd.imfine.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.init(applicationContext)

        if (!viewModel.checkHourlyEntry()) {
            createEntryDialog(this, viewModel::save)
        }

        binding.fab.setOnClickListener {
            createEntryDialog(this, viewModel::save)
        }

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            showFragment(viewModel.fragmentNameFor(item.itemId))
            binding.appBar.setExpanded(true)
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction().apply {
                listOf(HistoryFragment(), SettingsFragment(), HomeFragment()).forEach { fragment ->
                    add(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                    if (fragment.javaClass.simpleName == HomeFragment::class.java.simpleName) {
                        show(fragment)
                    } else {
                        hide(fragment)
                    }
                }
            }.commit()
            setToolbarTitle(getString(R.string.home))
        }
    }

    private fun showFragment(name: String) {
        supportFragmentManager.beginTransaction().apply {
            hide(supportFragmentManager.fragments.getCurrentFragment())
            show(fragmentOrThrow(name))
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }.commit()
    }

    private fun fragmentOrThrow(name: String): Fragment =
        supportFragmentManager.findFragmentByTag(name)
            ?: throw IllegalStateException("Fragment $name is not initialized")

    private fun List<Fragment>.getCurrentFragment(): Fragment {
        forEach { fragment -> if (fragment.isVisible) return fragment }
        throw IllegalStateException("No fragment currently visible")
    }

    fun setToolbarTitle(title: String) {
        binding.toolbarTitle.text = title
    }
}