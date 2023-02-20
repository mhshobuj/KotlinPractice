package com.example.kotlinpracticedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinpracticedemo.Adapter.MovieAdapter
import com.example.kotlinpracticedemo.Common.Constants
import com.example.kotlinpracticedemo.ViewModel.MovieViewModel
import com.example.kotlinpracticedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        viewModel.getPopularMovies(Constants.API_KEY)
        viewModel.observeMovieLiveData().observe(this, Observer { movieResponse ->
            movieAdapter.setMovieList(movieResponse.results, this)
        })
    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = movieAdapter
        }
    }
}