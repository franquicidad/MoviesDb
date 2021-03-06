package com.franco.moviesdb.ui.movie.comedy

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.*
import com.franco.moviesdb.ui.adapter.PagingAdapter
import com.franco.moviesdb.databinding.FragmentMovieComedyBinding
import com.franco.moviesdb.ui.adapter.PagingMovieComedyAdapter
import com.franco.moviesdb.util.collectFlow
import com.franco.moviesdb.util.lastVisibleEvents
import com.franco.moviesdb.util.onQueryTextChanged
import com.franco.moviesdb.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.ext.scope

@AndroidEntryPoint
class MovieComedyFragment : Fragment(R.layout.fragment_movie_comedy) {

    private val movieComedyVM: MovieComedyViewModel by viewModels()

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieComedyBinding.bind(view)
        val pagingAdapter = PagingMovieComedyAdapter(lifecycleScope)
        binding.apply {
            binding.rvListTypesMoviesAction.apply {
                adapter = pagingAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)

                lifecycleScope.apply {
                    collectFlow(rvListTypesMoviesAction.lastVisibleEvents) {
                        movieComedyVM.notifyLastVisible(it)
                    }

                    collectFlow(movieComedyVM.spinnerMovieComedy) {
                        progressMovieAction.visible = it
                    }


                }

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        movieComedyVM.notifyLastVisible((layoutManager as GridLayoutManager).findLastVisibleItemPosition())

                    }
                })
            }
        }

        movieComedyVM.movieComedyQuery.observe(viewLifecycleOwner, Observer {

            pagingAdapter.submitList(it)
        })
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            //update search query
            movieComedyVM.searchMovieComedyQuery.value = it
        }
    }
}

