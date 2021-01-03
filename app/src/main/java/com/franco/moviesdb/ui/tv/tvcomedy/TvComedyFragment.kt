package com.franco.moviesdb.ui.tv.tvcomedy


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.franco.moviesdb.*
import com.franco.moviesdb.databinding.FragmentTvComedyBinding
import com.franco.moviesdb.ui.adapter.PagingAdapter
import com.franco.moviesdb.util.collectFlow
import com.franco.moviesdb.util.lastVisibleEvents
import com.franco.moviesdb.util.onQueryTextChanged
import com.franco.moviesdb.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class TvComedyFragment : Fragment(R.layout.fragment_tv_comedy) {

    private val tvComedyVM: TvComedyViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTvComedyBinding.bind(view)
        val pagingAdapter = PagingAdapter(lifecycleScope)
        binding.apply {
            binding.rvListTypesMovies.apply {
                adapter = pagingAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)

                lifecycleScope.apply {
                    collectFlow(rvListTypesMovies.lastVisibleEvents) {
                        tvComedyVM.notifyLastVisible(it)
                    }

                    collectFlow(tvComedyVM.spinnerTvComedy) {
                        progressTvComedy.visible = it
                    }

                }

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        tvComedyVM.notifyLastVisible((layoutManager as GridLayoutManager).findLastVisibleItemPosition())

                    }
                })
            }
        }

        tvComedyVM.tvComedyQuery.observe(viewLifecycleOwner, Observer {
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
            tvComedyVM.searchTvComedyQuery.value = it
        }
    }
}
