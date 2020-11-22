package com.franco.moviesdb.ui.tv.tvcomedy


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.*
import com.franco.moviesdb.data.Entity.MoviesActionModel
import com.franco.moviesdb.data.Entity.TvActionModel
import com.franco.moviesdb.data.network.Api.MoviesActionApi
import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce
import com.franco.moviesdb.data.network.TvResponce.TvActionResponce
import retrofit2.Call
import retrofit2.Response


class TvComedyViewModel() : ViewModel() {

    private val _response = MutableLiveData<TvActionResponce>()//moviesActionResponce
    val response: LiveData<TvActionResponce> get() = _response

    private val _navigateToDetail = MutableLiveData<Event<Int>>()
    val navigateToDetail: LiveData<Event<Int>> get() = _navigateToDetail

    private val _items = MutableLiveData<List<TvActionModel>>()
    val items: LiveData<List<TvActionModel>> get() = _items

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {

        getActionMovies(APPEND_TV, ALONE_API, COMEDY, 1)
    }

    fun getActionMovies(
        appendMovieOrAction: String,
        aloneApi: String,
        numberActionOrMovieInt: Int,
        page: Int
    ) {

        MoviesActionApi.retrofitService.getTvAction(
            appendMovieOrAction,
            aloneApi,
            numberActionOrMovieInt,
            page
        )
            .enqueue(object :
                retrofit2.Callback<TvActionResponce> {
                override fun onResponse(
                    call: Call<TvActionResponce>,
                    response: Response<TvActionResponce>
                ) {
                    val obj: TvActionResponce? = response.body()
                    _response.value = obj
                }

                override fun onFailure(call: Call<TvActionResponce>, t: Throwable) {
                    statusMessage.value = Event("")

                    Log.e("", "ErrorRetreiveData: Failure: ${t.message}")
                }

            })
    }

    fun onMovieItemClicked(mediaItem: TvActionModel) {
        _navigateToDetail.value = Event(mediaItem.id)
    }
}