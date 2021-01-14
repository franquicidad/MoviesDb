package com.franco.moviesdb.ui.movieDetails

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.domain.SimilarMovies
import com.franco.moviesdb.repository.actorsRepository.ActorsRepository
import com.franco.moviesdb.repository.similarRepository.SimilarRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DetailViewModel @ViewModelInject constructor(
    private val repository: ActorsRepository,
    private val similarRepository: SimilarRepository
) : ViewModel() {

    suspend fun passTofunctionThoughtDetail(movieId: Int): Flow<List<Actor>> =
        repository.getAllActorsByMovie(movieId)

    suspend fun observableListActors(id: Int) {
        repository.addActorsByMovie(id)
    }

    suspend fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>> {

        val list = similarRepository.getSimilarMoviesByMovie(movieId)
        Log.d("similar", "${list.toList()}")
        return similarRepository.getSimilarMoviesByMovie(movieId)

    }

    private val _movieIdenti = MutableLiveData<Int>()
    val movieIdenti: LiveData<Int> get() = _movieIdenti

    var viewModelId: Int = 0

    @ExperimentalCoroutinesApi
    val lastVisible = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            val initId = viewModelId

            notifyLastVisible(viewModelId, lastVisible = 0)

        }
    }

    @ExperimentalCoroutinesApi
    fun notifyLastVisible(movieId: Int, lastVisible: Int) {
        viewModelScope.launch {
            similarRepository.checkRequireNewPageSimilarMovies(movieId, lastVisible)

        }

    }


}
