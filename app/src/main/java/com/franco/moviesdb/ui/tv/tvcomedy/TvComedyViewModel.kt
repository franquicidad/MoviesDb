package com.franco.moviesdb.ui.tv.tvcomedy


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.repository.tvComedyRepository.TvComedyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class TvComedyViewModel @ViewModelInject constructor(
        private val tvComedyRepositoty: TvComedyRepository
) : ViewModel() {

    private val _spinnerTvComedy = MutableStateFlow(true)
    val spinnerTvComedy: StateFlow<Boolean> get() = _spinnerTvComedy

    val searchTvComedyQuery = MutableStateFlow("")

    private val tvComedyQueryFlow = searchTvComedyQuery.flatMapLatest {
        tvComedyRepositoty.getTvListComedyByQuery(it)
    }
    val tvComedyQuery = tvComedyQueryFlow.asLiveData()

    init {
        viewModelScope.launch { notifyLastVisible(0) }
    }

    fun notifyLastVisible(lastVisible: Int) {
        viewModelScope.launch {
            tvComedyRepositoty.checkRequireNewPageTvComedy(lastVisible)
            _spinnerTvComedy.value = false
        }
    }

}