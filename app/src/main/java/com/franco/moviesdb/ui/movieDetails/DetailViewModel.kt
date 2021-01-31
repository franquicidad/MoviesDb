package com.franco.moviesdb.ui.movieDetails

import android.accounts.NetworkErrorException
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
import java.net.ConnectException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class DetailViewModel @ViewModelInject constructor(
    private val repository: ActorsRepository,
    private val similarRepository: SimilarRepository
) : ViewModel() {

    suspend fun passTofunctionThoughtDetail(movieId: Int): Flow<List<Actor>> {
        val list = repository.getAllActorsByMovie(movieId)

        return list
    }

    //Requires a contection
    suspend fun observableListActors(movieOrTv: String, id: Int) {
        repository.addActorsByMovie(movieOrTv, id)
    }

    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int> get() = _id

    private val _typeMovieOrTv = MutableLiveData<String>()
    val typeMovieOrTv: LiveData<String> get() = _typeMovieOrTv

    /**
     * SIMILAR MOVIES
     */
    suspend fun getSimilarMoviesByMovie(movieId: Int): Flow<List<SimilarMovies>> =
            similarRepository.getSimilarMoviesByMovie(movieId)


    private val _movieIdenti = MutableLiveData<Int>()
    val movieIdenti: LiveData<Int> get() = _movieIdenti

    var viewModelId: Int = 0
    var movieOrTv: String = ""

    @ExperimentalCoroutinesApi
    val lastVisible = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            try {
                notifyLastVisible(movieOrTv, viewModelId, lastVisible = 0)
            } catch (connection: ConnectException) {
            } catch (network: NetworkErrorException) {
            } catch (unknown: UnknownHostException) {
            }

        }
    }

    //NeedsInternet
    @ExperimentalCoroutinesApi
    fun notifyLastVisible(movieOrTv: String, movieId: Int, lastVisible: Int) {
        viewModelScope.launch {
            similarRepository.checkRequireNewPageSimilarMovies(movieOrTv, movieId, lastVisible)

        }

    }


}
