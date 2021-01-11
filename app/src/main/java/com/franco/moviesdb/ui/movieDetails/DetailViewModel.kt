package com.franco.moviesdb.ui.movieDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.repository.actorsRepository.ActorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel @ViewModelInject constructor(
        private val repository: ActorsRepository
) : ViewModel() {

    private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner

    suspend fun passTofunctionThoughtDetail(movieId: Int): Flow<List<Actor>> =
            repository.getAllActorsByMovie(movieId)


    suspend fun observableListActors(id: Int) {
        repository.addActorsByMovie(id)
    }


}
