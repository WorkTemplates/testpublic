package com.template.app.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.template.app.navigation.NavCmd

open class BaseFragment(@LayoutRes layout: Int): Fragment(layout) {

    fun navigate(navCmd: NavCmd){
        navCmd.execute(findNavController())
    }

}
