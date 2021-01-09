package com.franco.moviesdb.ui.movieDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.database.actors.model.ResponceWithActor
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel() {

    private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner


    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int> get() = _id

    suspend fun observableListActors(id: Int): LiveData<List<ResponceWithActor>> {
        val actorList = repository.getActorsFromDatabase(id)
        return actorList.asLiveData()
    }


}
