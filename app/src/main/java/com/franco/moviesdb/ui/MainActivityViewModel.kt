package com.franco.moviesdb.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.network.model.MoviesActionModel
import com.franco.moviesdb.network.model.MoviesActionResponce
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.util.ALONE_API
import com.franco.moviesdb.util.APPEND_MOVIE
import com.franco.moviesdb.util.Event
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel @ViewModelInject constructor(
    private val service: ApiService
) : ViewModel() {
//
//    private val _responsePopular = MutableLiveData<MoviesActionResponce>()//moviesActionResponce
//    val responsePopular: LiveData<MoviesActionResponce> get() = _responsePopular
//
//    private val _itemsPopular = MutableLiveData<List<MoviesActionModel>>()
//    val itemsPopular : LiveData<List<MoviesActionModel>> get() = _itemsPopular
//
//    private val statusMessagePopular = MutableLiveData<Event<String>>()
//
//    val message: LiveData<Event<String>>
//        get() = statusMessagePopular
//
//    init {
//        getPopularMovies(APPEND_MOVIE, ALONE_API)
//    }
//
//    fun getPopularMovies(
//        appendMovieOrAction: String,
//        aloneApi: String
//    ) {
//       service.getMoviesOrTvPopular(
//            appendMovieOrAction,
//            aloneApi,
//
//        )
//            .enqueue(object :
//                retrofit2.Callback<MoviesActionResponce> {
//                override fun onResponse(
//                    call: Call<MoviesActionResponce>,
//                    response: Response<MoviesActionResponce>
//                ) {
//                    val obj: MoviesActionResponce? = response.body()
//                    _responsePopular.value = obj
//                }
//
//                override fun onFailure(call: Call<MoviesActionResponce>, t: Throwable) {
//                    statusMessagePopular.value = Event("")
//                    Log.e("", "ErrorRetreiveData: Failure: ${t.message}")
//                }
//            })
//    }
}