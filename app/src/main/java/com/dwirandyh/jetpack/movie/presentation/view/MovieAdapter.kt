package com.dwirandyh.jetpack.movie.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dwirandyh.jetpack.databinding.ItemsMovieBinding
import com.dwirandyh.jetpack.movie.domain.model.MovieModel

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieList: ArrayList<MovieModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemsMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return this.movieList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(this.movieList[position])
    }


    inner class MovieViewHolder(val binding: ItemsMovieBinding) : ViewHolder(binding.root) {
        fun bind(movieModel: MovieModel) {
            binding.movie = movieModel
        }
    }

}