package com.franco.moviesdb.ui.tv.tvaction


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class TvActionViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _spinnerTvAction = MutableStateFlow(true)
    val spinnerTvAction: StateFlow<Boolean> get() = _spinnerTvAction

    val searchTvActionQuery = MutableStateFlow("")

    private val tvActionQueryFlow = searchTvActionQuery.flatMapLatest {
        repository.getTvListActionByQuery(it)
    }
    val tvActionQuery = tvActionQueryFlow.asLiveData()

    init {
        viewModelScope.launch { notifyLastVisible(0) }
    }

    fun notifyLastVisible(lastVisible: Int) {
        viewModelScope.launch {
            repository.checkRequireNewPageTvAction(lastVisible)
            _spinnerTvAction.value = false
        }
    }
}