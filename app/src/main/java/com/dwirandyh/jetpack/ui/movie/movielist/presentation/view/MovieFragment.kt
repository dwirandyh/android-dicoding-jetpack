package com.dwirandyh.jetpack.ui.movie.movielist.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwirandyh.jetpack.databinding.FragmentMovieBinding
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.data.MovieRepositoryImpl
import com.dwirandyh.jetpack.ui.movie.movielist.presentation.viewmodel.MovieViewModel
import com.dwirandyh.jetpack.ui.movie.movielist.presentation.viewmodel.MovieViewModelFactory

class MovieFragment : Fragment() {

    lateinit var adapter: MovieAdapter

    var viewModelFactory: MovieViewModelFactory = MovieViewModelFactory(MovieRepositoryImpl())

    lateinit var binding: FragmentMovieBinding
    lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentMovieBinding.inflate(inflater, container, false)

        this.setupAdapter()
        this.setupRecyclerView()

        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.setupViewModel()
        this.setupObserver()
    }

    private fun setupViewModel() {
        this.viewModel = ViewModelProvider(this, this.viewModelFactory).get(MovieViewModel::class.java)
        this.viewModel.loadMovie()
    }

    private fun setupObserver() {
        this.viewModel.movieList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    this.adapter.movieList = result.data
                    this.adapter.notifyDataSetChanged()

                    this.binding.loadingView.visibility = View.INVISIBLE
                    this.binding.movieRecycleView.visibility = View.VISIBLE
                }
                is Result.Loading -> {
                    this.binding.loadingView.visibility = View.VISIBLE
                    this.binding.movieRecycleView.visibility = View.INVISIBLE
                }

                is Result.Failure -> {
                    this.binding.loadingView.visibility = View.INVISIBLE
                    Toast.makeText(context, "Gagal mengambil data dari server", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupAdapter() {
        this.adapter = MovieAdapter(object : MovieItemListener {
            override fun openDetail(id: String) {
                view?.let {
                    val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(id)
                    it.findNavController().navigate(action)
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