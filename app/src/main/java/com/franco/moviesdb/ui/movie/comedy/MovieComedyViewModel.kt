package com.franco.moviesdb.ui.movie.comedy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.ALONE_API
import com.franco.moviesdb.APPEND_MOVIE
import com.franco.moviesdb.COMEDY
import com.franco.moviesdb.Event
import com.franco.moviesdb.data.network.Api.MoviesActionApi
import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce
import retrofit2.Call
import retrofit2.Response

class MovieComedyViewModel() : ViewModel() {


    private val _movieComedyresponse = MutableLiveData<MoviesActionResponce>()//moviesActionResponce
    val movieComedyresponse: LiveData<MoviesActionResponce> get() = _movieComedyresponse

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        callFunction()
    }

    fun callFunction() {
        getComedyMovies(APPEND_MOVIE, ALONE_API, COMEDY, 2)
    }

    fun getComedyMovies(
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
                    _movieComedyresponse.value = obj
                }
                override fun onFailure(call: Call<MoviesActionResponce>, t: Throwable) {
                    statusMessage.value = Event("")

                    Log.e("", "ErrorRetreiveData: Failure: ${t.message}")
                }
            })
    }
}
