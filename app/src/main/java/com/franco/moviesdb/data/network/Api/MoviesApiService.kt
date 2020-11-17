package com.franco.moviesdb.data.network.Api

import com.franco.moviesdb.data.network.MovieResponce.MoviesActionResponce
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesActionApiService{
    @GET("movie")
    fun getMoviesAction(@Query("with_genres" ) genre: Int?) : Deferred<List<MoviesActionResponce>>

    companion object{
        operator fun invoke(
            connectivityInterceptor : ConnectionInterceptor
        ): MovieDbInterfaceServiceToGetMovies {
            val requestInterceptor = Interceptor{ chain ->

                val url =chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", ALONE_API)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)

            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieDbInterfaceServiceToGetMovies::class.java)
        }
    }
}
//
//object MoviesActionApi{
//    val retrofitService:MoviesActionApiService by lazy {
//        retrofit.create(MoviesActionApiService::class.java)
//    }