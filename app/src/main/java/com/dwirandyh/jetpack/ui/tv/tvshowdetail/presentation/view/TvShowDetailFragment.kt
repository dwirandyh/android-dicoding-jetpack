package com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dwirandyh.jetpack.data.remote.RemoteDataSourceImpl
import com.dwirandyh.jetpack.data.repository.TvShowRepositoryImpl
import com.dwirandyh.jetpack.databinding.FragmentTvShowDetailBinding
import com.dwirandyh.jetpack.external.EspressoIdlingResource
import com.dwirandyh.jetpack.external.Result
import com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.viewmodel.TvShowDetailViewModel
import com.dwirandyh.jetpack.ui.tv.tvshowdetail.presentation.viewmodel.TvShowDetailViewModelFactory

class TvShowDetailFragment : Fragment() {

    var viewModelFactory: TvShowDetailViewModelFactory = TvShowDetailViewModelFactory(
        TvShowRepositoryImpl(RemoteDataSourceImpl())
    )
    lateinit var viewModel: TvShowDetailViewModel

    lateinit var binding: FragmentTvShowDetailBinding

    private val args: TvShowDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TvShowDetailViewModel::class.java)
        setupObserver()

        val tvId = args.id
        viewModel.loadTvShow(tvId)
        EspressoIdlingResource.increment()
    }

    private fun setupObserver() {
        viewModel.tvShow.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    binding.tvShow = result.data
                    binding.viewContentContainer.visibility = View.VISIBLE
                    binding.loadingView.root.visibility = View.INVISIBLE

                    if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
                is Result.Loading -> {
                    binding.viewContentContainer.visibility = View.INVISIBLE
                    binding.loadingView.root.visibility = View.VISIBLE
                }
                is Result.Failure -> {
                    binding.viewContentContainer.visibility = View.INVISIBLE
                    binding.loadingView.root.visibility = View.INVISIBLE
                    Toast.makeText(context, "Gagal mengambil data dari server", Toast.LENGTH_SHORT).show()

                    if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            }
        })
    }

}