package com.goldball.app.network

import com.goldball.app.model.PlayersResponse
import retrofit2.Response
import retrofit2.http.GET

interface PlayersRetrofit {

    @GET("https://ostats-23c52-default-rtdb.europe-west1.firebasedatabase.app/GoldPlayers.json")
    suspend fun getPlayers(): Response<PlayersResponse>
}
