package com.franco.moviesdb.ui.movie.action

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class MovieActionViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner


    val searchQuery = MutableStateFlow("")

    private val movieQueryFlow = searchQuery.flatMapLatest {
        repository.getMovieListByQuery(it)
    }
    val movieQuery = movieQueryFlow.asLiveData()

    init {
        viewModelScope.launch { notifyLastVisible(0) }
    }

    fun notifyLastVisible(lastVisible: Int) {
        viewModelScope.launch {
            repository.checkRequireNewPageMovieAction(lastVisible)
            _spinner.value = false
        }
    }
}