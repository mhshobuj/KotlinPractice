package com.example.kotlinpracticedemo.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpracticedemo.Model.Movies
import com.example.kotlinpracticedemo.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<Movies>()
    fun getPopularMovies(api_key: String) {
        RetrofitClient.api.getPopularMovies(api_key).enqueue(object  :
            Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<Movies> {
        return movieLiveData
    }
}