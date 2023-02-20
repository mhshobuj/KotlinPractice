package com.example.kotlinpracticedemo.Network

import com.example.kotlinpracticedemo.Model.MovieDetailsResponse
import com.example.kotlinpracticedemo.Model.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("popular?")
    fun getPopularMovies(
        @Query("api_key") api_key : String
    ) : Call<MovieListResponse>

    @GET("{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String
    ) : Call<MovieDetailsResponse>
}