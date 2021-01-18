package com.franco.moviesdb.ui.actorsDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.franco.moviesdb.R
import com.franco.moviesdb.databinding.ActorsDetailFragmentBinding
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.IMAGE_URL
import com.franco.moviesdb.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ActorsDetailFragment : Fragment(R.layout.actors_detail_fragment) {

    private val actorsDetailViewModel: ActorsDetailViewModel by viewModels()

    var actorId: Int? = null

    private lateinit var name:String
    private lateinit var bio:String
    private lateinit var birth:String
    //private lateinit var death:String
    //private lateinit var pageHome:String
    private lateinit var birthPlace:String
    private lateinit var path:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actorId = arguments?.getInt("actorId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding =ActorsDetailFragmentBinding.bind(view)

        lifecycleScope.launch {
            val parentJob = CoroutineScope(Dispatchers.IO).launch {
                val job1 = launch {
                    actorsDetailViewModel.getActorBioFromDatabase(actorId!!).collect {
                       name = it.name.toString()
                        bio=it.biography.toString()
                        birth =it.birthday.toString()
                        birthPlace=it.placeOfBirth
                        path=it.profilePath
                    }

                    actorsDetailViewModel.addActorToDatabase(actorId!!)

                }
//                val job2 = launch {
//                }
            }
            parentJob.invokeOnCompletion {
                val successOrError=it.toString()
                println("$successOrError")
            }

        }
        binding.apply {
            val url= IMAGE_URL+path
            actorName.text = name
            biography.text = bio
            dateOfBirth.text=birth
            placeOfBirth.text= birthPlace
            actorImage.loadUrl(url)

        }

    }

}