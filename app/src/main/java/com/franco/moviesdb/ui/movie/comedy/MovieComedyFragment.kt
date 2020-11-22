package com.franco.moviesdb.ui.movie.comedy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.adapter.MovieRecyclerAdapter
import com.franco.moviesdb.R
import com.franco.moviesdb.data.Entity.MoviesActionModel
import com.franco.moviesdb.databinding.FragmentMovieComedyBinding
import com.franco.moviesdb.newToast

class MovieComedyFragment : Fragment() {

    private var movieAdapter: MovieRecyclerAdapter? = null
    private lateinit var movieComedyViewModel: MovieComedyViewModel
    var movieComedyNavController: NavController? = null

    private lateinit var moviesComedyList: List<MoviesActionModel>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieComedyViewModel =
            ViewModelProvider(this).get(MovieComedyViewModel::class.java)
        val binding = FragmentMovieComedyBinding.inflate(layoutInflater)

        with(binding) {
            lifecycleOwner = this@MovieComedyFragment
            viewModel = movieComedyViewModel
        }

        moviesComedyList = mutableListOf<MoviesActionModel>()

        movieComedyViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                context?.newToast("No internet connection!!!, please connect")
            }
        })

        movieComedyViewModel.movieComedyresponse.observe(viewLifecycleOwner, Observer {

            moviesComedyList = it.movieActionList
            val linearLayoutManager = GridLayoutManager(context, 2)
            with(binding.rvListTypesMovies) {
                layoutManager = linearLayoutManager
                setHasFixedSize(true)
            }

            movieAdapter = MovieRecyclerAdapter(moviesComedyList)
            binding.rvListTypesMovies.adapter = movieAdapter

            movieAdapter!!.setUpListener(object : MovieRecyclerAdapter.ItemCLickedListener {
                override fun onItemClicked(modelItem: MoviesActionModel) {
                    val bundle = bundleOf(
                        "movieName" to modelItem.title,
                        "overview" to modelItem.overview,
                        "poster" to modelItem.posterPath,
                        "rating" to modelItem.rating,
                        "lang" to modelItem.originalLanguage,
                        "release" to modelItem.releaseDate,
                    )
                    movieComedyNavController = Navigation.findNavController(view!!)
                    movieComedyNavController!!.navigate(
                        R.id.action_navigation_dashboard_to_detailFragment,
                        bundle
                    )
                }
            })
        })

        return binding.root
    }
}