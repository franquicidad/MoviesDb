package com.franco.moviesdb.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.*
import com.franco.moviesdb.data.Entity.TvActionModel

import kotlin.properties.Delegates

class TvRecyclerAdapter(
    items: List<TvActionModel> = emptyList()
) : RecyclerView.Adapter<TvRecyclerAdapter.ViewHolder>() {

    lateinit var mItemCLicked: TvRecyclerAdapter.ItemTvCLickedListener


    var items: List<TvActionModel> by Delegates.observable(items) { _, _, _ ->
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
                mItemCLicked.onItemTvClicked(item)
            }
            //listenerMovie(item)
        }
    }

    fun setUpListener(itemCLicked: TvRecyclerAdapter.ItemTvCLickedListener) {
        mItemCLicked = itemCLicked
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Tengo que quitar el binding por error desconocido cannot find symbol class shape private ScrimScreenForRecyclerviewBinding
        // (@NonNull shape rootView) Un problema del viewBinding :(

        //val binding= ItemLayoutReclyclerMoviesAndTvBinding.bind(itemView)
        val imageView = itemView.findViewById<ImageView>(R.id.rv_image_movie)
        val txtTitle = itemView.findViewById<TextView>(R.id.movie_title)
        val progress = itemView.findViewById<ProgressBar>(R.id.progress_item)


        fun bind(movieModel: TvActionModel) {
            progress.visibility = View.VISIBLE

            val basePoster = IMAGE_URL
            val restOfImageUrl = movieModel.posterPath
            val completeUrl = basePoster + restOfImageUrl

            imageView.loadUrl(completeUrl, progress)
            txtTitle.loadTextTv(movieModel)

        }
    }

    interface ItemTvCLickedListener {
        fun onItemTvClicked(modelItem: TvActionModel)
    }
}
