package com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.dwirandyh.jetpack.R
import com.dwirandyh.jetpack.data.MovieRepositoryImpl
import com.dwirandyh.jetpack.databinding.FragmentMovieDetailBinding
import com.dwirandyh.jetpack.domain.model.MovieModel
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.external.convertToDate
import com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.viewmodel.MovieDetailViewModel
import com.dwirandyh.jetpack.ui.movie.moviedetail.presentation.viewmodel.MovieDetailViewModelFactory
import com.dwirandyh.jetpack.ui.movie.movielist.presentation.viewmodel.MovieViewModelFactory
import java.util.*

class MovieDetailFragment : Fragment() {

    var viewModelFactory: MovieDetailViewModelFactory = MovieDetailViewModelFactory(MovieRepositoryImpl())
    lateinit var viewModel: MovieDetailViewModel

    lateinit var binding: FragmentMovieDetailBinding

    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        setupObserver()

        val movieId = args.id
        viewModel.loadMovie(movieId)
    }

    private fun setupObserver() {
        viewModel.movie.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.movie = result.data
                    binding.viewContentContainer.visibility = View.VISIBLE
                    binding.loadingView.root.visibility = View.INVISIBLE
                }
                is Result.Loading -> {
                    binding.viewContentContainer.visibility = View.INVISIBLE
                    binding.loadingView.root.visibility = View.VISIBLE
                }
                is Result.Failure -> {
                    binding.viewContentContainer.visibility = View.INVISIBLE
                    binding.loadingView.root.visibility = View.INVISIBLE
                    Toast.makeText(context, "Gagal mengambil data dari server", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}