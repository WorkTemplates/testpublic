package com.goldball.app.network

import retrofit2.Response
import retrofit2.http.GET

interface AccessRetrofit {

    @GET("https://api.ipify.org/?format=string")
    suspend fun getIp():Response<String>

}
