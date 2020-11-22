package com.franco.moviesdb.ui.movieDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franco.moviesdb.IMAGE_URL
import com.franco.moviesdb.databinding.DetailFragmentBinding
import com.franco.moviesdb.loadUrl
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private lateinit var detailModel: DetailViewModel


    private lateinit var viewModel: DetailViewModel
    var movieName: String? = null
    var overview: String? = null
    var poster: String? = null
    var rating: Double? = null
    var lang: String? = null
    var release: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        detailModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)
        val binding = DetailFragmentBinding.inflate(layoutInflater)
        with(binding) {
            lifecycleOwner = this@DetailFragment
            viewModel = detailModel
        }
        val url = IMAGE_URL + poster

        with(binding) {
            titleDetail.text = movieName

            imagePosterDetail.loadUrl(url, binding.progressDetail)

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