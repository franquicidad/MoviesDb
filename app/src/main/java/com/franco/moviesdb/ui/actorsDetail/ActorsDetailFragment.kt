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
import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.ui.adapter.MovieListByActorAdapter
import com.franco.moviesdb.util.IMAGE_URL
import com.franco.moviesdb.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.actors_detail_fragment.*
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


        val binding = ActorsDetailFragmentBinding.bind(view)
        val movieListByActorAdapter = MovieListByActorAdapter(lifecycleScope)
        binding.recyclerMovieActor.apply {
            adapter = movieListByActorAdapter
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
        }

        actorsDetailViewModel.id.observe(viewLifecycleOwner, Observer {
            actorId = it
        })
        parentJob = lifecycleScope.launchWhenStarted {
            async(Dispatchers.IO) {
                actorsDetailViewModel.addActorToDatabase(actorId!!)

            }.await()

            launch(Dispatchers.IO) {
                val bio: ActorBiographyResponce? = actorsDetailViewModel.getActorBioFromDatabase(actorId!!)


                async(Dispatchers.Main) {
                    bio?.let {
                        binding.actorName.text = bio.name

                        val url: String?
                        url = IMAGE_URL + bio.profilePath

                        binding.actorImage.loadUrl(url)

                        binding.dateOfBirth.text = bio.birthday.toString()
                        binding.placeOfBirth.text = bio.placeOfBirth
                        binding.biograph.text = bio.biography
                    }

                }.await()

                async(Dispatchers.IO) {
                    actorsDetailViewModel.retreiveAndAddToDb(actorId!!)
                }.await()

                async(Dispatchers.Main) {

                    val list = actorsDetailViewModel.getAllMoviesActorId(actorId!!)

                    list.let {
                        it.collect {
                            Log.i("Any", "$it")


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