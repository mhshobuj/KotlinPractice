package com.example.kotlinpracticedemo.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpracticedemo.Model.MovieDetailsResponse
import com.example.kotlinpracticedemo.Model.MovieListResponse
import com.example.kotlinpracticedemo.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<MovieDetailsResponse>()
    fun getMovieDetails(movie_id: Int, api_key: String) {
        RetrofitClient.api.getMovieDetails(movie_id, api_key).enqueue(object:
            Callback<MovieDetailsResponse> {
            override fun onResponse(call: Call<MovieDetailsResponse>, response: Response<MovieDetailsResponse>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<MovieDetailsResponse> {
        return movieLiveData
    }
}
