package com.franco.moviesdb.data.network.Api

import com.franco.moviesdb.BASE_URL
import com.franco.moviesdb.data.Entity.MoviesActionModel
import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MoviesActionApiService {
    @GET("movie")
    fun getMoviesAction(@Query("with_genres") genre: Int?): Call<String>

    @GET("{sort_criteria}")
    fun getMoviesAction(
        @Path("sort_criteria") sort_criteria: String,
        @Query("api_key") api_key: String?,
        @Query("with_genres") genre: Int?,
        @Query("page") page: Int?,

        ): Call<MoviesActionResponce>
//    @GET("movie")
//    fun getMoviesAction(@Query("with_genres" ) genre: Int?) : Deferred<List<MoviesActionResponce>>


}

object MoviesActionApi {
    val retrofitService: MoviesActionApiService by lazy {
        retrofit.create(MoviesActionApiService::class.java)
    }
}
