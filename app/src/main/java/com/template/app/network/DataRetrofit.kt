package com.template.app.network

import com.template.app.model.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface DataRetrofit {

    @GET("url")
    suspend fun getData(): Response<NetworkResponse>
}
