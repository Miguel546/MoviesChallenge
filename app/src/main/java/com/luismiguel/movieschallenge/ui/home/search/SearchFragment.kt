package com.luismiguel.movieschallenge.ui.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.luismiguel.movieschallenge.R
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusLocal
import com.luismiguel.movieschallenge.databinding.FragmentSearchBinding
import com.luismiguel.movieschallenge.ui.home.HomeActivity
import com.luismiguel.movieschallenge.ui.home.HomeViewModel
import com.luismiguel.movieschallenge.ui.home.search.rv.SearchAdapter
import com.luismiguel.movieschallenge.utils.Constants
import com.luismiguel.movieschallenge.utils.textTvErrorUpcoming
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    @Inject
    lateinit var searchingAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return searchBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecyclerView()
        initObservers()
        initObservers()
    }

    private fun initObservers(){
        lifecycleScope.launch {
            launch {
                searchViewModel.moviesDBList.collect {
                    when(it.status){
                        DataStatusLocal.Status.LOADING -> {
                            showLoading()
                        }

                        DataStatusLocal.Status.SUCCESS -> {
                            hideLoading()
                            hideError()
                            val moviesList = it.data
                            if(!moviesList.isNullOrEmpty()){
                                searchingAdapter.differ.submitList(moviesList)
                            }else{
                                dataStatusError("No se encontraron películas")
                            }
                        }
                        DataStatusLocal.Status.ERROR -> {
                            dataStatusError(error = it.message)
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView(){
        searchBinding.recyclerViewMS.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchingAdapter
        }
        searchingAdapter.setOnClickMovieListener { movie ->
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(movie))
        }
    }

    private fun dataStatusError(error: String?){
        hideLoading()
        showError()
        searchBinding.tvError.text = textTvErrorUpcoming(error ?: Constants.ERROR_UNEXPECTED)
    }

    private fun showLoading(){
        (activity as HomeActivity).loadingVisible(true)
    }

    private fun hideLoading(){
        (activity as HomeActivity).loadingVisible(false)
    }

    private fun initView() {
        homeViewModel.putNumeroFragments(2)
        setTitleSearch()
    }

    private fun setTitleSearch(){
        (activity as HomeActivity).setTitle(getString(R.string.peliculas_buscadas))
    }

    private fun showError() {
        searchBinding.tvError.isVisible = true
        searchBinding.nsvMoviesSearch.isVisible = false
        searchBinding.recyclerViewMS.isVisible = false
    }

    private fun hideError() {
        searchBinding.tvError.isVisible = false
        searchBinding.nsvMoviesSearch.isVisible = true
        searchBinding.recyclerViewMS.isVisible = true
    }
}