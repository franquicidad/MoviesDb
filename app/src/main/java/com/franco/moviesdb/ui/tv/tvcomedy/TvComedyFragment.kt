package com.franco.moviesdb.ui.tv.tvcomedy


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
import com.franco.moviesdb.data.Entity.TvActionModel
import com.franco.moviesdb.databinding.FragmentTvComedyBinding
import com.franco.moviesdb.newToast


class TvComedyFragment : Fragment() {

    private var tvAdapter: TvRecyclerAdapter? = null
    private lateinit var tvComedyVM: TvComedyViewModel
    private lateinit var tvComedyList: List<TvActionModel>
    var tvComedyNavController: NavController? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvComedyVM =
            ViewModelProvider(this).get(TvComedyViewModel::class.java)
        val binding = FragmentTvComedyBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        binding.viewModel = tvComedyVM

        tvComedyList = mutableListOf<TvActionModel>()

        tvComedyVM.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                context?.newToast("No internet connection!!!, please connect")
            }
        })

        tvComedyVM.response.observe(viewLifecycleOwner, Observer {

            tvComedyList = it.tvList
            val linearLayoutManager = GridLayoutManager(context, 2)
            with(binding.rvListTypesMovies) {
                layoutManager = linearLayoutManager
                setHasFixedSize(true)
            }


            tvAdapter =
                TvRecyclerAdapter(tvComedyList)
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


                    tvComedyNavController = Navigation.findNavController(view!!)
                    tvComedyNavController!!.navigate(
                        R.id.action_navigation_tv_comedy_to_detailFragment,
                        bundle
                    )


                }

            })
        })

        tvComedyVM.items.observe(viewLifecycleOwner, Observer {
            tvAdapter?.items = it
        })

        return binding.root
    }
}

