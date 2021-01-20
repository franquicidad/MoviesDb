package com.franco.moviesdb.ui.actorsDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.franco.moviesdb.R
import com.franco.moviesdb.databinding.ActorsDetailFragmentBinding
import com.franco.moviesdb.ui.adapter.MovieListByActorAdapter
import com.franco.moviesdb.util.IMAGE_URL
import com.franco.moviesdb.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ActorsDetailFragment : Fragment(R.layout.actors_detail_fragment) {

    private val actorsDetailViewModel: ActorsDetailViewModel by viewModels()

    var actorId: Int? = null


    private var parentJob:Job?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actorId = arguments?.getInt("actorId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        actorsDetailViewModel.id.observe(viewLifecycleOwner, Observer {
            actorId = it
        })
        val binding = ActorsDetailFragmentBinding.bind(view)
        val movieListByActorAdapter = MovieListByActorAdapter(lifecycleScope)
        binding.recyclerMovieActor.apply {
            adapter = movieListByActorAdapter
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
        }
        parentJob = lifecycleScope.launchWhenStarted {
            async(Dispatchers.IO) {
                actorsDetailViewModel.addActorToDatabase(actorId!!)

            }.await()

            launch(Dispatchers.IO) {
                val bio = actorsDetailViewModel.getActorBioFromDatabase(actorId!!)

                async(Dispatchers.Main) {
                    val url = IMAGE_URL + bio.profilePath
                    binding.actorImage.loadUrl(url)

                    binding.actorName.text = bio.name
                    binding.dateOfBirth.text = bio.birthday.toString()
                    binding.placeOfBirth.text = bio.placeOfBirth
                    binding.biograph.text = bio.biography
                }.await()

                async(Dispatchers.IO) {
                    actorsDetailViewModel.retreiveAndAddToDb(actorId!!)
                }.await()

                launch {

                    val list = actorsDetailViewModel.getAllMoviesActorId(actorId!!)
                    Log.i("Any", "$list")

                    list.let {
                        it.collect {
                            movieListByActorAdapter.submitList(it)
                        }
                    }
                }
            }

        }


        }


    override fun onDestroy() {
        super.onDestroy()
        parentJob?.cancel()
    }

}