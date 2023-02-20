package com.example.kotlinpracticedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlinpracticedemo.Common.Constants
import com.example.kotlinpracticedemo.Model.MovieDetailsResponse
import com.example.kotlinpracticedemo.ViewModel.MovieDetailsViewModel
import com.example.kotlinpracticedemo.ViewModel.MovieViewModel
import com.example.kotlinpracticedemo.databinding.ActivityMainBinding
import com.example.kotlinpracticedemo.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailsBinding
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie_id:Int = intent.getIntExtra("MOVIE_ID", 0)
        Log.e("Details", "ID: $movie_id")

        movieDetailsViewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]

        movieDetailsViewModel.getMovieDetails(movie_id,Constants.API_KEY)
        movieDetailsViewModel.observeMovieLiveData().observe(this) { movieDetailsResponse ->
            setDetailsData(movieDetailsResponse)
        }
    }

    private fun setDetailsData(movieDetailsResponse: MovieDetailsResponse?) {
        Glide.with(this)
            .load(Constants.IMAGE_BASE_URL + movieDetailsResponse!!.poster_path)
            .into(binding.headerImage)
        binding.movieTitle.text = movieDetailsResponse!!.title
        binding.movieOverview.text = movieDetailsResponse!!.overview
        binding.movieBudget.text = movieDetailsResponse!!.budget.toString()
        binding.movieReleaseDate.text = movieDetailsResponse!!.release_date
        binding.movieRuntime.text = movieDetailsResponse!!.runtime.toString()
        for (i in 0 until movieDetailsResponse.genres.size){
            binding.movieGenre.text = movieDetailsResponse.genres[i].name
        }
        binding.movieRating.text = movieDetailsResponse!!.vote_average.toString()

    }
}