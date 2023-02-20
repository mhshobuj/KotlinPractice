package com.example.kotlinpracticedemo.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinpracticedemo.Common.Constants
import com.example.kotlinpracticedemo.Model.MovieListResponse
import com.example.kotlinpracticedemo.MovieDetailsActivity
import com.example.kotlinpracticedemo.databinding.MovieLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movieList = ArrayList<MovieListResponse.Result>()
    private lateinit var context: Context

    fun setMovieList(movieList: List<MovieListResponse.Result>, context: Context) {
        this.movieList = movieList as ArrayList<MovieListResponse.Result>
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
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("MOVIE_ID", movieList[position].id)
            context.startActivity(intent)
            ///Toast.makeText(context,"" + movieList[position].original_title,Toast.LENGTH_SHORT).show()
            Log.e("Movie", "ID: " + movieList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}