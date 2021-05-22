package com.goldball.app.repository

import androidx.lifecycle.LiveData
import com.goldball.app.model.Player

interface PlayersRepository {
    fun getPlayers(): LiveData<List<Player>>
}
