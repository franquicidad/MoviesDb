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
import com.franco.moviesdb.databinding.ItemLayoutReclyclerMoviesAndTvBinding
import com.franco.moviesdb.domain.ActorListMovies
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.util.IMAGE_URL
import com.franco.moviesdb.util.collectFlow
import com.franco.moviesdb.util.loadUrl
import com.franco.moviesdb.util.onClickEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MovieListByActorAdapter(private val scope: CoroutineScope) :
        ListAdapter<ActorListMovies, MovieListByActorAdapter.ItemViewHolder>(DiffCallBackFromMovieActor()) {

    var navController: NavController? = null

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLayoutReclyclerMoviesAndTvBinding.bind(itemView)

        @ExperimentalCoroutinesApi
        fun bind(item: ActorListMovies) = with(binding) {

            val url = IMAGE_URL + item.posterPath
            with(binding) {
                movieTitle.text = item.title
                binding.rvImageMovie.loadUrl(url)
            }


        }

        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_layout_reclycler_movies_and_tv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents) {
            val movieItemId = item.id
            val url = IMAGE_URL + item.posterPath

            val bundle = bundleOf(
                    "id" to item.id,
                    "movieName" to item.title,
                    "overview" to item.overview,
                    "poster" to url,
                    "rating" to item.rating,
                    "lang" to item.originalLanguage,
                    "release" to item.releaseDate,
                    "backimage" to item.backdropPath
            )
            navController = Navigation.findNavController(it)
            navController!!.navigate(R.id.action_actorsDetailFragment_to_detailFragment, bundle)


        }
    }
}

class DiffCallBackFromMovieActor : DiffUtil.ItemCallback<ActorListMovies>() {
    override fun areItemsTheSame(oldItem: ActorListMovies, newItem: ActorListMovies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
            oldItem: ActorListMovies,
            newItem: ActorListMovies
    ): Boolean {
        return oldItem == newItem
    }

}