package com.luismiguel.movieschallenge.ui.home.search.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.luismiguel.movieschallenge.R
import com.luismiguel.movieschallenge.databinding.ItemMovieBinding
import com.luismiguel.movieschallenge.domain.model.MovieModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchAdapter @Inject constructor(): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemMovieBinding.inflate(inflater, parent, false)
        return SearchViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovieBinding.bind(view)
        fun setData(item: MovieModel){

            binding.tvTitle.text = item.title

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${item.posterpath}")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_access_time)
                .error(R.drawable.ic_broken_image)
                .centerCrop()
                .into(binding.imgMovie)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }

    }

    private var onItemClickListener: ((MovieModel) -> Unit) ?= null

    fun setOnClickMovieListener(listener: (MovieModel) -> Unit){
        onItemClickListener = listener
    }

    private val differCallback = object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
}