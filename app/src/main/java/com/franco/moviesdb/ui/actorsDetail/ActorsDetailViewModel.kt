package com.franco.moviesdb.ui.actorsDetail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.domain.ActorListMovies
import com.franco.moviesdb.repository.actorsBioRepository.actorBioRepository
import com.franco.moviesdb.repository.actorsBioRepository.actorBioRepositoryImpl
import com.franco.moviesdb.repository.actorsMovieListRepository.ActorsMovieListRepository
import com.franco.moviesdb.repository.actorsMovieListRepository.ActorsMovieListRepositoryImpl
import com.franco.moviesdb.util.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class ActorsDetailViewModel @ViewModelInject constructor(
        private val actorBioRepository: actorBioRepositoryImpl,
        private val actorMovieListRepo: ActorsMovieListRepositoryImpl
) : ViewModel() {


    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int> get() = _id

    /**
     * Functions for biography Information
     */
    suspend fun getActorBioFromDatabase(actorId: Int): ActorBiographyResponce =
            actorBioRepository.getActorBioFromDb(actorId)


    suspend fun addActorToDatabase(actorId: Int) =
            actorBioRepository.retreiveAndAddToDatabase(actorId)

    /**
     * Functions To retreive movies Related to the Actor
     */


    suspend fun getAllMoviesActorId(actorId: Int): Flow<List<ActorListMovies>> {
        val list = actorMovieListRepo.getAllMoviesByActorId(actorId)
        Log.i("ListB", "$list")
        return list

    }


    suspend fun retreiveAndAddToDb(actorId: Int) {

        actorMovieListRepo.retreiveFromNetAddToDB(actorId)

    }


}