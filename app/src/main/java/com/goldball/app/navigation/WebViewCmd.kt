package com.goldball.app.navigation

import androidx.navigation.NavController
import com.goldball.app.ui.main.MainFragmentDirections

class WebViewCmd(val url: String): NavCmd {

    override fun execute(controller: NavController) {

        val action = MainFragmentDirections.actionGlobalNavigationWebview(url)
        controller.navigate(action)
    }
}
