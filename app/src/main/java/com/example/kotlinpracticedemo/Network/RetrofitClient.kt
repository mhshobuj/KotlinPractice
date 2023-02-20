package com.example.kotlinpracticedemo.Network

import com.example.kotlinpracticedemo.Common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}