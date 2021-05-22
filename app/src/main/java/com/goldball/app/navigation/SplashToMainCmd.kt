package com.goldball.app.navigation

import androidx.navigation.NavController
import com.goldball.app.ui.splash.SplashFragmentDirections

class SplashToMainCmd: NavCmd {
    override fun execute(controller: NavController) {
        val action = SplashFragmentDirections.actionNavigationSplashToNavigationMain()
        controller.navigate(action)
    }
}
