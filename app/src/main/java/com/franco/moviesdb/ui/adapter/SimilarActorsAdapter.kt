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
import com.franco.moviesdb.*
import com.franco.moviesdb.databinding.ItemActorsBinding
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.util.IMAGE_URL
import com.franco.moviesdb.util.collectFlow
import com.franco.moviesdb.util.loadUrl
import com.franco.moviesdb.util.onClickEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SimilarActorsAdapter(private val scope: CoroutineScope) :
        ListAdapter<Actor, SimilarActorsAdapter.ItemViewHolder>(DiffCallBackFromSimilarAdapterActors()) {

    var navController: NavController? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_actors, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item, position)
        scope.collectFlow(itemView.onClickEvents) {
            val actorId = item.Id

            val bundle = bundleOf(
                    "actorId" to actorId
            )
            navController = Navigation.findNavController(it!!)
            navController!!.navigate(R.id.action_similarDetailFragment_to_actorsDetailFragment, bundle)

        }
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemActorsBinding.bind(itemView)
        fun bind(item: Actor, position: Int) = with(binding) {
            val url: String? = IMAGE_URL + item.urlImage

            if (url != null) {
                actorImage.loadUrl(url)
            } else {
                actorImage.loadUrl("https://st3.depositphotos.com/3581215/18899/v/600/depositphotos_188994514-stock-illustration-vector-illustration-male-silhouette-profile.jpg")
            }
            actorName.text = item.name
            actorRole.text = item.character

        }
    }

}

class DiffCallBackFromSimilarAdapterActors : DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.Id == newItem.Id
    }

    override fun areContentsTheSame(
            oldItem: Actor,
            newItem: Actor
    ): Boolean {
        return oldItem == newItem
    }

}



