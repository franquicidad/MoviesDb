package com.franco.moviesdb.data.network.Api

import com.franco.moviesdb.BASE_URL
import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce
import com.franco.moviesdb.data.network.TvResponce.TvActionResponce
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MoviesActionApiService {

    @GET("{sort_criteria}")
    fun getMoviesAction(
        @Path("sort_criteria") sort_criteria: String,
        @Query("api_key") api_key: String?,
        @Query("with_genres") genre: Int?,
        @Query("page") page: Int?,

        ): Call<MoviesActionResponce>

    @GET("{sort_criteria}")
    fun getTvAction(
        @Path("sort_criteria") sort_criteria: String,
        @Query("api_key") api_key: String?,
        @Query("with_genres") genre: Int?,
        @Query("page") page: Int?,

        ): Call<TvActionResponce>
}

object MoviesActionApi {
    val retrofitService: MoviesActionApiService by lazy {
        retrofit.create(MoviesActionApiService::class.java)
    }
}
