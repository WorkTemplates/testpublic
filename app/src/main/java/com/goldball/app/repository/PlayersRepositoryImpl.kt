package com.goldball.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goldball.app.model.Player
import com.goldball.app.network.PlayersRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class PlayersRepositoryImpl(val retrofit: PlayersRetrofit) : PlayersRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val playersLiveData = MutableLiveData<List<Player>>()

    private var players: List<Player> = ArrayList()
        set(value) {
            field = value
            playersLiveData.postValue(value)
        }

    override fun getPlayers(): LiveData<List<Player>> {
        launch {
            val newPlayers = runCatching {
                val result = retrofit.getPlayers()
                if (result.code() == 200) {
                    val c = result.body()
                    return@runCatching c as List<Player>
                } else {
                    return@runCatching null
                }
            }
            newPlayers.getOrNull()?.let { players = it.sortedBy { player -> player.position } }
        }
        return playersLiveData
    }

}
