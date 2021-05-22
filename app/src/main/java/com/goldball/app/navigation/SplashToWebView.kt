package com.goldball.app.navigation

import androidx.navigation.NavController
import com.goldball.app.ui.splash.SplashFragmentDirections

class SplashToWebView(val url: String): NavCmd {
    override fun execute(controller: NavController) {
        val action = SplashFragmentDirections.actionNavigationSplashToWebview(url)
        controller.navigate(action)
    }
}
