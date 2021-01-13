package com.franco.moviesdb.ui.movieDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.repository.actorsRepository.ActorsRepository
import com.franco.moviesdb.repository.similarRepository.SimilarRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
        private val repository: ActorsRepository,
        private val similarRepository: SimilarRepository
) : ViewModel() {

    suspend fun passTofunctionThoughtDetail(movieId: Int): Flow<List<Actor>> =
            repository.getAllActorsByMovie(movieId)

    suspend fun observableListActors(id: Int) {
        repository.addActorsByMovie(id)
    }

    suspend fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>> =
            similarRepository.getSimilarMoviesByMovie(movieId)

    private val _movieIdenti = MutableLiveData<Int>()
    val movieIdenti: LiveData<Int> get() = _movieIdenti

    var viewModelId: Int = 0

    val lastVisible = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            lastVisible.collect {
                notifyLastVisible(viewModelId, lastVisible = it)
            }

        }
    }

    @ExperimentalCoroutinesApi
    private suspend fun notifyLastVisible(movieId: Int, lastVisible: Int) {
        similarRepository.checkRequireNewPageSimilarMovies(movieId, lastVisible)

    }


}
