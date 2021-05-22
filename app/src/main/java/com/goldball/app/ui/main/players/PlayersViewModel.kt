package com.goldball.app.ui.main.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.goldball.app.model.Player
import com.goldball.app.repository.PlayersRepository

class PlayersViewModel(
    val repo: PlayersRepository
): ViewModel() {

    fun getPlayers(): LiveData<List<Player>> = repo.getPlayers()
}
