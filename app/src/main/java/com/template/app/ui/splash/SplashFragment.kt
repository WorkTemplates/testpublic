package com.template.app.ui.splash

import android.os.Bundle
import com.template.app.R
import com.template.app.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment(R.layout.splash_fragment) {

    private val viewModel: SplashViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigation.observe(viewLifecycleOwner) {
            navigate(it)
        }
        viewModel.update()
    }

}
