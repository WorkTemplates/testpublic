package com.template.app.network

import com.template.app.model.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface DataRetrofit {

    @GET("https://ostats-23c52-default-rtdb.europe-west1.firebasedatabase.app/GoldPlayers.json")
    suspend fun getData(): Response<NetworkResponse>
}
