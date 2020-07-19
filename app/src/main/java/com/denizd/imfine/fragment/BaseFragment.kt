package com.denizd.imfine.fragment

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.denizd.imfine.activity.MainActivity
import com.denizd.imfine.viewmodel.BaseViewModel

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    private lateinit var _context: Context

    protected abstract val binding: ViewBinding
    protected abstract val titleId: Int
    protected abstract val viewModel: BaseViewModel

    protected val mainActivity: MainActivity get() = activity as MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
    }

    override fun getContext(): Context = _context

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) mainActivity.setToolbarTitle(getString(titleId))
    }
}