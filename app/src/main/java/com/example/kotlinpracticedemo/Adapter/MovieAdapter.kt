package com.example.kotlinpracticedemo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinpracticedemo.Common.Constants
import com.example.kotlinpracticedemo.Model.Movies
import com.example.kotlinpracticedemo.databinding.MovieLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movieList = ArrayList<Movies.Result>()
    private lateinit var context: Context

    fun setMovieList(movieList: List<Movies.Result>, context: Context) {
        this.movieList = movieList as ArrayList<Movies.Result>
        this.context = context

        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(Constants.IMAGE_BASE_URL + movieList[position].poster_path)
            .into(holder.binding.movieImage)
        holder.binding.movieName.text = movieList[position].title

        holder.itemView.setOnClickListener {
            Toast.makeText(context,"" + movieList[position].original_title,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}