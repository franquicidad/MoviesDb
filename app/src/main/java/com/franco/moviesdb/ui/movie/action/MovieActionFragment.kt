package com.franco.moviesdb.ui.movie.action

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.Event
import com.franco.moviesdb.adapter.MovieRecyclerAdapter
import com.franco.moviesdb.R
import com.franco.moviesdb.data.Entity.MoviesActionModel
import com.franco.moviesdb.databinding.FragmentMovieActionBinding
import com.franco.moviesdb.newToast

class MovieActionFragment : Fragment() {

    private var movieAdapter: MovieRecyclerAdapter? = null
    private lateinit var movieActionVM: MovieActionViewModel
    var navController: NavController? = null
    private lateinit var moviesActionList: List<MoviesActionModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieActionVM =
            ViewModelProvider(this).get(MovieActionViewModel::class.java)

        val binding = FragmentMovieActionBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this

        binding.viewModel = movieActionVM

        moviesActionList = mutableListOf<MoviesActionModel>()

        movieActionVM.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                context?.newToast("No internet connection!!!, please connect")
            }
        })

        movieActionVM.response.observe(viewLifecycleOwner, Observer {

            moviesActionList = it.movieActionList
            val linearLayoutManager = GridLayoutManager(context, 2)
            with(binding.rvListTypesMovies) {
                layoutManager = linearLayoutManager
                setHasFixedSize(true)
            }

            movieAdapter = MovieRecyclerAdapter(moviesActionList)
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
                    navController = Navigation.findNavController(view!!)
                    navController!!.navigate(R.id.action_navigation_home_to_detailFragment, bundle)
                }
            })
        })


        return binding.root
    }


}