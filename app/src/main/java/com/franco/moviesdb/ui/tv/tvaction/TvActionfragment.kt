package com.franco.moviesdb.ui.tv.tvaction

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
import com.franco.moviesdb.adapter.TvRecyclerAdapter
import com.franco.moviesdb.R
import com.franco.moviesdb.adapter.MovieRecyclerAdapter
import com.franco.moviesdb.data.Entity.MoviesActionModel
import com.franco.moviesdb.data.Entity.TvActionModel
import com.franco.moviesdb.databinding.FragmentTvActionBinding
import com.franco.moviesdb.newToast

class TvActionFragment : Fragment() {

    private var tvAdapter: TvRecyclerAdapter? = null
    private lateinit var tvActionVM: TvActionViewModel
    private lateinit var tvActionList: List<TvActionModel>
    var tvActionNavController: NavController? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvActionVM =
            ViewModelProvider(this).get(TvActionViewModel::class.java)
        val binding = FragmentTvActionBinding.inflate(layoutInflater)


        binding.lifecycleOwner = this

        binding.viewModel = tvActionVM

        tvActionList = mutableListOf<TvActionModel>()

        tvActionVM.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                context?.newToast("No internet connection!!!, please connect")
            }
        })

        tvActionVM.response.observe(viewLifecycleOwner, Observer {

            tvActionList = it.tvList
            val linearLayoutManager = GridLayoutManager(context, 2)
            with(binding.rvListTypesMovies) {
                layoutManager = linearLayoutManager
                setHasFixedSize(true)
            }


            tvAdapter = TvRecyclerAdapter(tvActionList)
            binding.rvListTypesMovies.adapter = tvAdapter

            tvAdapter!!.setUpListener(object : TvRecyclerAdapter.ItemTvCLickedListener {
                override fun onItemTvClicked(modelItem: TvActionModel) {
                    val bundle = bundleOf(
                        "movieName" to modelItem.name,
                        "overview" to modelItem.overview,
                        "poster" to modelItem.posterPath,
                        "rating" to modelItem.rating,
                        "lang" to modelItem.originalLanguage,
                        "release" to modelItem.first_air_date

                    )


                    tvActionNavController = Navigation.findNavController(view!!)
                    tvActionNavController!!.navigate(
                        R.id.action_navigation_tv_action_to_detailFragment,
                        bundle
                    )


                }

            })
        })

        tvActionVM.items.observe(viewLifecycleOwner, Observer {
            tvAdapter?.items = it
        })

        return binding.root
    }
}

