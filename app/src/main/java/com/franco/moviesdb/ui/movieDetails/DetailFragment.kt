package com.franco.moviesdb.ui.movieDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.R
import com.franco.moviesdb.databinding.DetailFragmentBinding
import com.franco.moviesdb.ui.adapter.ActorsAdapter
import com.franco.moviesdb.ui.adapter.PagingSimilarMoviesAdapter
import com.franco.moviesdb.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val detailModel: DetailViewModel by viewModels()

    var typeMovieOrTv: String? = null
    var theSelectedRecyclerViewid: Int? = null
    var movieName: String? = null
    var overview: String? = null
    var posterImage: String? = null
    var rating: Double? = null
    var lang: String? = null
    var release: String? = null
    var backdropImage: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        typeMovieOrTv = arguments?.getString("movieOrTv")
        theSelectedRecyclerViewid = arguments?.getInt("id")
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
            val linearLayout = LinearLayoutManager(requireContext())
            linearLayout.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = linearLayout
            setHasFixedSize(true)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val scrollId = theSelectedRecyclerViewid
                    detailModel.notifyLastVisible(
                        typeMovieOrTv!!,
                        scrollId!!,
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    )

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

            collectFlow(rvSimilar.lastVisibleEventsLinearActors) {
                detailModel.lastVisible.value = it
            }

            detailModel.notifyLastVisible(typeMovieOrTv!!, theSelectedRecyclerViewid!!, 0)

            val theId: Int? = theSelectedRecyclerViewid
            detailModel.id.observe(viewLifecycleOwner, Observer {
                theSelectedRecyclerViewid = it
            })

            detailModel.typeMovieOrTv.observe(viewLifecycleOwner, Observer {
                typeMovieOrTv = it
            })


            detailModel.viewModelId = theSelectedRecyclerViewid!!

            detailModel.movieOrTv = typeMovieOrTv!!

            detailModel.observableListActors(typeMovieOrTv!!, theSelectedRecyclerViewid!!)


            val parentJob = CoroutineScope(IO).launch {
                val job1 = launch {
                    theId?.let { id ->
                        val second = id
                        detailModel.passTofunctionThoughtDetail(second)
                            .collect { listOfActorsForMovie ->
                                Log.i("Anf", "$listOfActorsForMovie")
                                pagingAdapter.submitList(listOfActorsForMovie)
                            }
                    }
                }

                val job2 = launch {
                    theId?.let {
                        val oneId = theSelectedRecyclerViewid
                        detailModel.getSimilarMoviesByMovie(oneId!!).collect {
                            if (it.size != 0) {
                                val list = it
                                Log.i("Sim", "$list")
                                similarAdapter.submitList(it)
                            } else {
                                detailModel.notifyLastVisible(
                                    typeMovieOrTv!!,
                                    theSelectedRecyclerViewid!!,
                                    0
                                )
                            }
                        }
                    }
                }
            }

            parentJob.invokeOnCompletion {
                println("Both jobs completed")
            }


        }
    }

    fun RecyclerView.setHorizontalLayout() {
        val linearLayout = LinearLayoutManager(requireContext())
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager = linearLayout
        setHasFixedSize(true)
    }
}








