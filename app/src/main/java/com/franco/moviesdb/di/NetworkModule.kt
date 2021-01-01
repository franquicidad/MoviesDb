package com.franco.moviesdb.di

import android.content.Context
import androidx.room.Room
import com.franco.moviesdb.database.LocalDatasourceImpl
import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.domain.Repository
import com.franco.moviesdb.domain.RepositoryImpl
import com.franco.moviesdb.network.RemoteDatasourceImpl
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.ui.movie.action.MovieActionViewModel
import com.franco.moviesdb.ui.movie.comedy.MovieComedyViewModel
import com.franco.moviesdb.ui.movieDetails.DetailViewModel
import com.franco.moviesdb.ui.tv.tvaction.TvActionViewModel
import com.franco.moviesdb.ui.tv.tvcomedy.TvComedyViewModel
import com.franco.moviesdb.util.BASE_URL
import com.franco.moviesdb.util.DATABASE_MOVIE_NAME
import com.franco.moviesdb.util.DATABASE_TV_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)

object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        DATABASE_MOVIE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        val client = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideDao(db: MovieDatabase) = db.moviesDAO()

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDatasourceImpl,
        remoteDataSource: RemoteDatasourceImpl
    ): Repository {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun providesRepoToMovieActionVm(repositoryImpl: RepositoryImpl): MovieActionViewModel {
        return MovieActionViewModel(repositoryImpl)
    }

    fun providesRepoToMovieComedyVm(repositoryImpl: RepositoryImpl): MovieComedyViewModel {
        return MovieComedyViewModel(repositoryImpl)
    }

    fun providesRepoToTvActionVm(repositoryImpl: RepositoryImpl): TvActionViewModel {
        return TvActionViewModel(repositoryImpl)
    }

    fun providesRepoToTvComedyVm(repositoryImpl: RepositoryImpl): TvComedyViewModel {
        return TvComedyViewModel(repositoryImpl)
    }

    @Singleton
    @Provides
    fun provideDetailViewModel(repositoryImpl: RepositoryImpl): DetailViewModel {
        return DetailViewModel(repositoryImpl)
    }


//    @Singleton
//    @Provides
//    fun providesRepoToVm(service: ApiService): MovieActionViewModel {
//        return MovieActionViewModel(service)
//    }
}