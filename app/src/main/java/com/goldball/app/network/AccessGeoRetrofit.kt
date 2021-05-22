package com.goldball.app.network

import com.goldball.app.model.GeoResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccessGeoRetrofit {
    @GET("https://geo.ipify.org/api/v1")
    suspend fun getGeo(@Query("apiKey") apiKey: String, @Query("ip") ip: String): Response<GeoResult>
}
