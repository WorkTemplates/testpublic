package com.goldball.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.goldball.app.lifecycle.LiveEvent
import com.goldball.app.model.Player
import com.goldball.app.navigation.NavCmd
import com.goldball.app.navigation.WebViewCmd
import com.goldball.app.ui.main.players.PlayerClickCallback

class MainViewModel : ViewModel(), PlayerClickCallback {

    private val _navigation =  LiveEvent<NavCmd>()
    val navigation: LiveData<NavCmd> = _navigation


    override fun onPlayerClick(player: Player) {
        _navigation.postValue(WebViewCmd(player.url))
    }



}
