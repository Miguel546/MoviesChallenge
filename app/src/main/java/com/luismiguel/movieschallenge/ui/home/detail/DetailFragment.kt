package com.luismiguel.movieschallenge.ui.home.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.luismiguel.movieschallenge.R
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusLocal
import com.luismiguel.movieschallenge.databinding.FragmentDetailBinding
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.ui.home.HomeActivity
import com.luismiguel.movieschallenge.ui.home.HomeViewModel
import com.luismiguel.movieschallenge.utils.textOverviewDetail
import com.luismiguel.movieschallenge.utils.textReleaseDetail
import com.luismiguel.movieschallenge.utils.textTvTitleDetail
import com.luismiguel.movieschallenge.utils.textVoteAverageDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleDetail()
        homeViewModel.putNumeroFragments(3)

        val detailMovie = args.movieItem
        val id = detailMovie.id ?: -1
        detailViewModel.getMovieById(id)
        initObservers(detailMovie)
        detailBinding.apply {
            Glide.with(this@DetailFragment)
                .load("https://image.tmdb.org/t/p/w500/${detailMovie.posterpath}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_access_time)
                .error(R.drawable.ic_broken_image)
                .centerCrop()
                .into(imgMovie)

            tvTitle.text = textTvTitleDetail(detailMovie.title ?: "")
            tvVote.text = textVoteAverageDetail(detailMovie.voteaverage.toString())
            tvLanzamiento.text = textReleaseDetail(detailMovie.releasedate ?: "")
            tvDescription.text = textOverviewDetail(detailMovie.overview ?: "")

        }
    }

    private fun initObservers(detailMovie: MovieModel) {
        lifecycleScope.launch {
            launch {
                detailViewModel.upcomingMovie.collect {
                    when (it.status) {
                        DataStatusLocal.Status.LOADING -> {
                            showLoading()
                            hideLoading()
                        }

                        DataStatusLocal.Status.SUCCESS -> {
                            val movies = it.data
                            if(movies.isNullOrEmpty()){
                                detailViewModel.addMovieDB(detailMovie)
                                Toast.makeText(context, "Película guardada.", Toast.LENGTH_SHORT).show()
                            }
                        }

                        DataStatusLocal.Status.ERROR -> {
                            hideLoading()
                            Toast.makeText(context, "Ocurrió un error ${it.message}.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }

    private fun showLoading(){
        (activity as HomeActivity).loadingVisible(true)
    }

    private fun hideLoading(){
        (activity as HomeActivity).loadingVisible(false)
    }

    private fun setTitleDetail(){

        (activity as HomeActivity).setTitle(getString(R.string.detalle_pelicula))
    }
}