package com.example.kotlinpracticedemo.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpracticedemo.Model.MovieListResponse
import com.example.kotlinpracticedemo.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<MovieListResponse>()
    fun getPopularMovies(api_key: String) {
        RetrofitClient.api.getPopularMovies(api_key).enqueue(object  :
            Callback<MovieListResponse> {
            override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<MovieListResponse> {
        return movieLiveData
    }
}