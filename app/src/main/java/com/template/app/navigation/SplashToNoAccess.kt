package com.template.app.navigation

import androidx.navigation.NavController
import com.template.app.ui.splash.SplashFragmentDirections

class SplashToNoAccess: NavCmd {
    override fun execute(controller: NavController) {
        val action = SplashFragmentDirections.actionNavigationSplashToNoAccess()
        controller.navigate(action)
    }
}
