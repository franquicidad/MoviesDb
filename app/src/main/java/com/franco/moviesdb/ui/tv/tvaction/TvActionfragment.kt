package com.franco.moviesdb.ui.tv.tvaction

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
import com.franco.moviesdb.databinding.FragmentTvActionBinding
import com.franco.moviesdb.ui.adapter.PagingAdapter
import com.franco.moviesdb.ui.adapter.PagingTvActionAdapter
import com.franco.moviesdb.util.collectFlow
import com.franco.moviesdb.util.lastVisibleEvents
import com.franco.moviesdb.util.onQueryTextChanged
import com.franco.moviesdb.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class TvActionFragment : Fragment(R.layout.fragment_tv_action) {

    private val tvActionVM: TvActionViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTvActionBinding.bind(view)
        val pagingAdapter = PagingTvActionAdapter(lifecycleScope)
        binding.apply {
            binding.rvListTypesMovies.apply {
                adapter = pagingAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)

                lifecycleScope.apply {
                    collectFlow(rvListTypesMovies.lastVisibleEvents) {
                        tvActionVM.notifyLastVisible(it)
                    }

                    collectFlow(tvActionVM.spinnerTvAction) {
                        progressTv.visible = it
                    }

                }

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        tvActionVM.notifyLastVisible((layoutManager as GridLayoutManager).findLastVisibleItemPosition())
                    }
                })
            }
        }
        tvActionVM.tvActionQuery.observe(viewLifecycleOwner, Observer {
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
            tvActionVM.searchTvActionQuery.value = it
        }
    }
}

