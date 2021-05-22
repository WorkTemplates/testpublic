package com.goldball.app.navigation

import androidx.navigation.NavController

interface NavCmd {
    fun execute(controller: NavController)
}
