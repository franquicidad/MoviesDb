package com.franco.moviesdb.ui.movie.action

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.ACTION
import com.franco.moviesdb.ALONE_API
import com.franco.moviesdb.APPEND_MOVIE
import com.franco.moviesdb.Event
import com.franco.moviesdb.data.Entity.MoviesActionModel
import com.franco.moviesdb.data.network.Api.MoviesActionApi
import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce
import retrofit2.Call
import retrofit2.Response


class MovieActionViewModel() : ViewModel() {

    private val _response = MutableLiveData<MoviesActionResponce>()//moviesActionResponce
    val response: LiveData<MoviesActionResponce> get() = _response

    private val _items = MutableLiveData<List<MoviesActionModel>>()
    val items: LiveData<List<MoviesActionModel>> get() = _items

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {

        getActionMovies(APPEND_MOVIE, ALONE_API, ACTION, 2)
    }

    fun getActionMovies(
        appendMovieOrAction: String,
        aloneApi: String,
        numberActionOrMovieInt: Int,
        page: Int
    ) {

        MoviesActionApi.retrofitService.getMoviesAction(
            appendMovieOrAction,
            aloneApi,
            numberActionOrMovieInt,
            page
        )
            .enqueue(object :
                retrofit2.Callback<MoviesActionResponce> {
                override fun onResponse(
                    call: Call<MoviesActionResponce>,
                    response: Response<MoviesActionResponce>
                ) {
                    val obj: MoviesActionResponce? = response.body()
                    _response.value = obj
                }

                override fun onFailure(call: Call<MoviesActionResponce>, t: Throwable) {
                    statusMessage.value = Event("")
                    Log.e("", "ErrorRetreiveData: Failure: ${t.message}")
                }

            })
    }

}