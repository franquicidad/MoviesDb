package com.franco.moviesdb.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.franco.moviesdb.R
import com.franco.moviesdb.util.IMAGE_URL
import com.franco.moviesdb.databinding.DetailFragmentBinding
import com.franco.moviesdb.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val detailModel: DetailViewModel by viewModels()

    var id: Int? = null
    var movieName: String? = null
    var overview: String? = null
    var poster: String? = null
    var rating: Double? = null
    var lang: String? = null
    var release: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = arguments?.getInt("id")
        movieName = arguments?.getString("movieName")
        overview = arguments?.getString("overview")
        poster = arguments?.getString("poster")
        rating = arguments?.getDouble("rating")
        lang = arguments?.getString("lang")
        release = arguments?.getString("release")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view= inflater.inflate(R.layout.detail_fragment, container, false)

        val binding = DetailFragmentBinding.inflate(layoutInflater)
        with(binding) {
            lifecycleOwner = this@DetailFragment
            viewModel = detailModel
        }


        with(binding) {
            titleDetail.text = movieName

            imagePosterDetail.loadUrl(poster.toString())

            movieListDisplayRating.rating = rating?.toFloat()!!

            detailOverview.text = overview

            ratingNumber.text = rating?.toFloat().toString()
            titleDetail.text = movieName

            language.text = lang
            releaseDate.text = release
        }

        return binding.root
    }


}