package com.franco.moviesdb.ui.movie.comedy

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.domain.MovieActionDomain
import com.franco.moviesdb.domain.Repository
import com.franco.moviesdb.domain.RepositoryImpl
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.COMEDY
import com.franco.moviesdb.util.POPULARITY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class MovieComedyViewModel @ViewModelInject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _spinnerMovieComedy = MutableStateFlow(true)
    val spinnerMovieComedy: StateFlow<Boolean> get() = _spinnerMovieComedy

    val searchMovieComedyQuery = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    private val movieComedyQueryFlow = searchMovieComedyQuery.flatMapLatest {
        repository.getMovieListComedyByQuery(it)
    }
    val movieComedyQuery = movieComedyQueryFlow.asLiveData()

    init {
        viewModelScope.launch {

        notifyLastVisible(0)

        }

    }

    fun notifyLastVisible(lastVisible: Int) {
        Log.i("stac", "viewModel")
        viewModelScope.launch {
            repository.checkRequireNewPageMovieComedy(lastVisible)
            _spinnerMovieComedy.value = false
        }
    }
}