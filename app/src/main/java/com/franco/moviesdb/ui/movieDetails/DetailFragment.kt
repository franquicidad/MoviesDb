package com.franco.moviesdb.ui.movieDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.R
import com.franco.moviesdb.databinding.DetailFragmentBinding
import com.franco.moviesdb.ui.adapter.ActorsAdapter
import com.franco.moviesdb.ui.adapter.PagingSimilarMoviesAdapter
import com.franco.moviesdb.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val detailModel: DetailViewModel by viewModels()

    var id: Int? = null
    var movieName: String? = null
    var overview: String? = null
    var posterImage: String? = null
    var rating: Double? = null
    var lang: String? = null
    var release: String? = null
    var backdropImage: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getInt("id")
        movieName = arguments?.getString("movieName")
        overview = arguments?.getString("overview")
        posterImage = arguments?.getString("poster")
        rating = arguments?.getDouble("rating")
        lang = arguments?.getString("lang")
        release = arguments?.getString("release")
        backdropImage = arguments?.getString("backimage")

    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DetailFragmentBinding.bind(view)
        val pagingAdapter = ActorsAdapter(lifecycleScope)
        val similarAdapter = PagingSimilarMoviesAdapter(lifecycleScope)
        framelayout_actors.apply {
            adapter = pagingAdapter
            setHorizontalLayout()
        }
        rvSimilar.apply {
            //setAdapter
            adapter = similarAdapter
            setHorizontalLayout()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    detailModel.lastVisible.value = ((layoutManager as GridLayoutManager).findLastVisibleItemPosition())

                }
            })
        }
        binding.apply {

            with(binding) {
                titleDetail.text = movieName
                val cardviewImage = posterImage
                val backImage = backdropImage.toString()

                poster.loadUrl(cardviewImage.toString())

                imagePosterDetail.loadUrl(backImage.toString())

                movieListDisplayRating.rating = rating?.toFloat()!!

                detailOverview.text = overview

                ratingNumber.text = rating?.toFloat().toString()
                titleDetail.text = movieName

                language.text = lang
                releaseDate.text = release
            }


        }

        lifecycleScope.launch {
            val theId: Int? = id

            detailModel.viewModelId = id!!

            theId?.let { detailModel.observableListActors(it) }

            theId?.let { id ->
                val second = id
                detailModel.passTofunctionThoughtDetail(second).collect { listOfActorsForMovie ->
                    pagingAdapter.submitList(listOfActorsForMovie)

                }
                detailModel.getSimilarMoviesByMovie(second).collect {
                    similarAdapter.submitList(it)
                }
            }


//            if (theId != null) {
//                detailModel.observableListActors(theId).observe(viewLifecycleOwner, Observer {
//                    pagingAdapter.submitList(it)
//
//                })
//            }

        }


//            collectFlow(detailModel.spinner) {
//                actorsProgress.visible = it
//            }

    }

    private fun RecyclerView.setHorizontalLayout() {
        val linearLayout = LinearLayoutManager(requireContext())
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager = linearLayout
        setHasFixedSize(true)
    }
}





