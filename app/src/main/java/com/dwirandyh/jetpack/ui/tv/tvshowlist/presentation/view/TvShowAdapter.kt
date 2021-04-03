package com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dwirandyh.jetpack.databinding.ItemsTvShowBinding
import com.dwirandyh.jetpack.domain.model.TvShowModel
import com.dwirandyh.jetpack.ui.movie.movielist.presentation.view.MovieItemListener

class TvShowAdapter(val listener: TvShowItemListener) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    var tvList: ArrayList<TvShowModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(
            ItemsTvShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun getItemCount(): Int {
        return this.tvList.count()
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(this.tvList[position])
    }


    inner class TvShowViewHolder(val binding: ItemsTvShowBinding, val listener: TvShowItemListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvModel: TvShowModel) {
            binding.tvShow = tvModel
            binding.listener = listener
        }
    }

}