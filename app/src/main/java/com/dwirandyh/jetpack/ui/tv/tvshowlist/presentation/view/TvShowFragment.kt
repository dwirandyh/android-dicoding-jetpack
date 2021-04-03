package com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.view

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
import com.dwirandyh.jetpack.databinding.FragmentTvShowBinding
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.data.TvShowRepositoryImpl
import com.dwirandyh.jetpack.ui.movie.movielist.presentation.view.MovieFragmentDirections
import com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.viewmodel.TvShowViewModel
import com.dwirandyh.jetpack.ui.tv.tvshowlist.presentation.viewmodel.TvShowViewModelFactory

class TvShowFragment : Fragment() {

    lateinit var adapter: TvShowAdapter

    var viewModelFactory: TvShowViewModelFactory = TvShowViewModelFactory(TvShowRepositoryImpl())

    lateinit var binding: FragmentTvShowBinding
    lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentTvShowBinding.inflate(inflater, container, false)

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
        this.viewModel = ViewModelProvider(this, this.viewModelFactory).get(TvShowViewModel::class.java)
        this.viewModel.loadTvShow()
    }

    private fun setupObserver() {
        this.viewModel.tvShowList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    this.adapter.tvList = result.data
                    this.adapter.notifyDataSetChanged()

                    this.binding.loadingView.visibility = View.INVISIBLE
                    this.binding.tvRecycleView.visibility = View.VISIBLE
                }
                is Result.Loading -> {
                    this.binding.loadingView.visibility = View.VISIBLE
                    this.binding.tvRecycleView.visibility = View.INVISIBLE
                }

                is Result.Failure -> {
                    Toast.makeText(context, "Gagal mengambil data dari server", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupAdapter() {
        this.adapter = TvShowAdapter(object : TvShowItemListener {
            override fun openDetail(id: String) {
                view?.let {
                    val action = TvShowFragmentDirections.actionTvFragmentToTvShowDetailFragment(id)
                    it.findNavController().navigate(action)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        this.binding.tvRecycleView.layoutManager = LinearLayoutManager(context)
        this.binding.tvRecycleView.setHasFixedSize(true)
        this.binding.tvRecycleView.adapter = adapter
    }
}