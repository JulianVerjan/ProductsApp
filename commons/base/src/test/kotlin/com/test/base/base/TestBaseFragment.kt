package com.test.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.products.base.BaseFragment

class TestBaseFragment : BaseFragment<ViewDataBinding, ViewModel>(
    layoutId = 0
) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = null

    override fun onInitDependencyInjection() {}
}
