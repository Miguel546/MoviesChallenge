package com.luismiguel.movieschallenge.ui.home.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.luismiguel.movieschallenge.R
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.databinding.FragmentUpcomingBinding
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.ui.home.HomeActivity
import com.luismiguel.movieschallenge.ui.home.HomeViewModel
import com.luismiguel.movieschallenge.ui.home.upcoming.rv.UpcomingAdapter
import com.luismiguel.movieschallenge.utils.Constants.ERROR_UNEXPECTED
import com.luismiguel.movieschallenge.utils.textTvErrorUpcoming
import com.luismiguel.movieschallenge.utils.textTvPagActualUpcoming
import com.luismiguel.movieschallenge.utils.textTvTotal
import com.luismiguel.movieschallenge.utils.textTvTotalUpcoming
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private lateinit var upcomingBinding: FragmentUpcomingBinding
    private val upcomingViewModel: UpcomingViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    var maximo = 1

    @Inject
    lateinit var upcomingAdapter: UpcomingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        upcomingBinding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return upcomingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.putNumeroFragments(1)
        //upcomingViewModel.callMovies()
        setupRecyclerView()
        initView()
        observe()
    }

    private fun initView() {
        setTitleUpcoming()

        upcomingBinding.btnSearch.setOnClickListener {
            hideKeyboard(it)
            val numeroET = upcomingBinding.etPagina.text.toString()
            if(numeroET.isNotEmpty()){
                val numero = numeroET.toInt()
                if(numero in 1..maximo){
                    upcomingViewModel.callMovies(numero)
                }else{
                    MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.ic_error2)
                        .setTitle("Error")
                        .setMessage("Página inválida.")
                        .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }else{
                MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_error)
                    .setTitle("Error")
                    .setMessage("El número de página no puede estar vacía.")
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }


        }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch{
                    upcomingViewModel.upcomingMovies.collect {
                        when(it.status){
                            DataStatusRemote.Status.LOADING -> {
                                showLoading()
                            }

                            DataStatusRemote.Status.SUCCESS -> {
                                val upcomingMovies = it.data
                                hideLoading()
                                if(upcomingMovies != null) {
                                    upcomingBinding.tvTotal.text = textTvTotal(upcomingMovies.totalpages)
                                    val totalpages = upcomingMovies.totalpages
                                    val paginaActual = upcomingMovies.page
                                    maximo = totalpages
                                    poblarRV(upcomingMovies.results, paginaActual, totalpages)
                                }else{
                                    dataStatusError(it.message)
                                }
                            }
                            DataStatusRemote.Status.ERROR -> {
                                dataStatusError(error = it.message)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun dataStatusError(error: String?){
        hideLoading()
        showError()
        upcomingBinding.tvError.text = textTvErrorUpcoming(error ?: ERROR_UNEXPECTED)
    }

    private fun showLoading(){
        (activity as HomeActivity).loadingVisible(true)
    }

    private fun hideLoading(){
        (activity as HomeActivity).loadingVisible(false)
    }

    private fun showError() {
        upcomingBinding.tvError.isVisible = true
        upcomingBinding.tvPagina.isVisible = false
        upcomingBinding.nsvMovies.isVisible = false
        upcomingBinding.recyclerView.isVisible = false
        upcomingBinding.btnSearch.isVisible = false
        upcomingBinding.etPagina.isVisible = false
        upcomingBinding.tvTotal.isVisible = false
    }

    private fun poblarRV(listaPeliculas: List<MovieModel>, paginaActual: Int, maxPaginas: Int){
        hideError()
        upcomingAdapter.differ.submitList(listaPeliculas)

        upcomingBinding.tvTotal.text = textTvTotalUpcoming(maxPaginas.toString())

        upcomingBinding.tvPagina.text = textTvPagActualUpcoming(paginaActual.toString())

    }

    private fun hideError() {
        upcomingBinding.tvError.isVisible = false
        upcomingBinding.tvPagina.isVisible = true
        upcomingBinding.nsvMovies.isVisible = true
        upcomingBinding.recyclerView.isVisible = true
        upcomingBinding.btnSearch.isVisible = true
        upcomingBinding.etPagina.isVisible = true
        upcomingBinding.tvTotal.isVisible = true
    }

    private fun setupRecyclerView() {
        upcomingBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = upcomingAdapter
        }
        upcomingAdapter.setOnClickMovieListener {movie ->
            findNavController().navigate(UpcomingFragmentDirections.actionUpcomingFragmentToDetailFragment(movie))
        }
    }

    private fun hideKeyboard(view: View) {
        (activity as HomeActivity).hideKeyboard(view)
    }

    private fun setTitleUpcoming(){
        (activity as HomeActivity).setTitle(getString(R.string.proximas_peliculas))
    }
}