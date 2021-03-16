package com.dwirandyh.jetpack.movie.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwirandyh.jetpack.databinding.FragmentMovieBinding
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.movie.presentation.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private val adapter = MovieAdapter()

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentMovieBinding.inflate(inflater, container, false)

        this.setupViewModel()
        this.setupObserver()
        this.setupRecyclerView()

        return this.binding.root
    }

    private fun setupViewModel() {
        this.viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        this.viewModel.loadMovie()
    }

    private fun setupObserver() {
        this.viewModel.movieList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    this.adapter.movieList = result.data
                    this.adapter.notifyDataSetChanged()

                    this.binding.loadingView.visibility = View.INVISIBLE
                }
                is Result.Loading -> {
                    this.binding.loadingView.visibility = View.VISIBLE
                }

                is Result.Failure -> {
                    // Handle Error
                }
            }
        })
    }

    private fun setupRecyclerView() {
        this.binding.movieRecycleView.layoutManager = LinearLayoutManager(context)
        this.binding.movieRecycleView.setHasFixedSize(true)
        this.binding.movieRecycleView.adapter = adapter
    }
}