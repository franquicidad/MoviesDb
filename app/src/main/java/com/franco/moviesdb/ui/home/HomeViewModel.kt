package com.franco.moviesdb.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.ACTION
import com.franco.moviesdb.ALONE_API
import com.franco.moviesdb.APPEND_MOVIE
import com.franco.moviesdb.data.Entity.MoviesActionModel
import com.franco.moviesdb.data.network.Api.MovieActionProperty
import com.franco.moviesdb.data.network.Api.MoviesActionApi
import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {

    private val _response = MutableLiveData<MoviesActionResponce>()//moviesActionResponce
    val response: LiveData<MoviesActionResponce> get() = _response

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init {
        getActionMovies()
    }

    private fun getActionMovies() {
        MoviesActionApi.retrofitService.getMoviesAction(APPEND_MOVIE, ALONE_API, ACTION, 1)
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
                    Log.e("", "ErrorRetreiveData: Failure: ${t.message}")
                }

            })
    }
}