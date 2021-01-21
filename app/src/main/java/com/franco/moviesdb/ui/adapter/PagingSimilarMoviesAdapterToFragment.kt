package com.franco.moviesdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.R
import com.franco.moviesdb.databinding.ItemSimilarBinding
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.util.IMAGE_URL
import com.franco.moviesdb.util.collectFlow
import com.franco.moviesdb.util.loadUrl
import com.franco.moviesdb.util.onClickEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PagingSimilarMoviesAdapterToFragment(private val scope: CoroutineScope) :
        ListAdapter<SimilarMovies, PagingSimilarMoviesAdapterToFragment.ItemViewHolder>(DiffCallBackSimilarToDetail()) {
    var navController: NavController? = null


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSimilarBinding.bind(itemView)
        fun bind(item: SimilarMovies) = with(binding) {
            val url = IMAGE_URL + item.posterPath

            var movieOrTvName = ""

            if (item.title == null || item.title == "") {
                movieOrTvName = item.originalName.toString()
            } else {
                movieOrTvName = item.title
            }
            similarMovieTitle.text = movieOrTvName
            similarImage.loadUrl(url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_similar, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents) {
            val url = IMAGE_URL + item.posterPath
            val backImage = IMAGE_URL + item.backdropPath
            var movieOrTvName = ""
            var isMovieOrTv = ""

            if (item.title == null || item.title == "") {
                movieOrTvName = item.originalName.toString()
                isMovieOrTv = "tv"
            } else {
                movieOrTvName = item.title
                isMovieOrTv = "movie"
            }

            val bundle = bundleOf(
                    "movieOrTv" to isMovieOrTv,
                    "id" to item.id,
                    "movieName" to movieOrTvName,
                    "overview" to item.overview,
                    "poster" to url,
                    "rating" to item.rating,
                    "lang" to item.originalLanguage,
                    "release" to item.releaseDate,
                    "backimage" to backImage


            )
            navController = Navigation.findNavController(it!!)
            navController!!.navigate(R.id.action_similarDetailFragment_to_detailFragment, bundle)

        }
    }
}

class DiffCallBackSimilarToDetail : DiffUtil.ItemCallback<SimilarMovies>() {
    override fun areItemsTheSame(oldItem: SimilarMovies, newItem: SimilarMovies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SimilarMovies, newItem: SimilarMovies): Boolean {
        return oldItem.equals(newItem)
    }


}