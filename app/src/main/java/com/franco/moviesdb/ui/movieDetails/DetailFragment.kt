package com.franco.moviesdb.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.franco.moviesdb.R
import com.franco.moviesdb.databinding.DetailFragmentBinding
import com.franco.moviesdb.ui.adapter.ActorsAdapter
import com.franco.moviesdb.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        framelayout_actors.apply {
            adapter = pagingAdapter
            val linearLayout = LinearLayoutManager(requireContext())
            linearLayout.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = linearLayout
            setHasFixedSize(true)
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

//        lifecycleScope.apply {
//            collectFlow(framelayout_actors.lastVisibleEventsLinearActors) {
//                id?.let { it1 -> detailModel.notifyLastVisible(it1) }
//            }

        lifecycleScope.launch {
            val theId: Int? = id

            if (theId != null) {
                detailModel.observableListActors(theId).observe(viewLifecycleOwner, Observer {
                    pagingAdapter.submitList(it)

                })
            }

        }


//            collectFlow(detailModel.spinner) {
//                actorsProgress.visible = it
//            }

    }
    }





