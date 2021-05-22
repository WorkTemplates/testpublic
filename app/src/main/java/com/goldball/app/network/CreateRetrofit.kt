package com.goldball.app.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

fun createPlayersRetrofit(): PlayersRetrofit {
    val client = OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://ostats-23c52-default-rtdb.europe-west1.firebasedatabase.app")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(PlayersRetrofit::class.java)
}

fun createAccessRetrofit(): AccessRetrofit {
    val client = OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.ipify.org")
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    return retrofit.create(AccessRetrofit::class.java)
}

fun createAccessGeoRetrofit(): AccessGeoRetrofit {
    val client = OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://geo.ipify.org")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(AccessGeoRetrofit::class.java)
}
