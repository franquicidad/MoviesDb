package com.franco.moviesdb.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.*
import com.franco.moviesdb.database.moviesAction.MoviesActionModel

import kotlin.properties.Delegates
class MovieRecyclerAdapter(items: List<MoviesActionModel> = emptyList()) :
    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    lateinit var mItemCLicked: ItemCLickedListener

    var items: List<MoviesActionModel> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_layout_reclycler_movies_and_tv)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        holder.bind(item)
        holder.itemView.setOnClickListener {
            mItemCLicked.let {
                mItemCLicked.onItemClicked(item)
            }
        }
    }

    fun setUpListener(itemCLicked: ItemCLickedListener) {
        mItemCLicked = itemCLicked
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.rv_image_movie)
        val txtTitle = itemView.findViewById<TextView>(R.id.movie_title)
        val progress = itemView.findViewById<ProgressBar>(R.id.progress_item)

        fun bind(movieModel: MoviesActionModel) {
            progress.visibility = View.VISIBLE
            val basePoster = IMAGE_URL
            val restOfImageUrl = movieModel.posterPath
            val completeUrl = basePoster + restOfImageUrl
            imageView.loadUrl(completeUrl, progress)
            txtTitle.loadTextMovie(movieModel)
        }
    }

    interface ItemCLickedListener {
        fun onItemClicked(modelItem: MoviesActionModel)
    }
}
