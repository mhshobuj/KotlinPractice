package com.example.kotlinpracticedemo.Network

import com.example.kotlinpracticedemo.Model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key : String) : Call<Movies>
}