package com.franco.moviesdb.di

import android.content.Context
import androidx.room.Room
import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.database.actors.localDatasourceActors.LocalDatasourceActorsImpl
import com.franco.moviesdb.database.actors.remoteDatasourceActors.RemoteDatasourceActorsImpl
import com.franco.moviesdb.database.localDatasources.movies.localDataSourceMoviecomedy.LocalDataSourceMovieComedyImpl
import com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvAction.LocalDataSourceTvActionImpl
import com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvComedy.LocalDataSourceTvComedyImpl
import com.franco.moviesdb.database.localDatasources.movies.localdatasourceMovieAction.LocalDatasourceMoviesActionImpl
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.remoteDatasourceMovieAction.RemoteDatasourceMovieActionImpl
import com.franco.moviesdb.network.remoteDatasourceMoviecomedy.RemoteDatasourceMovieComedyImpl
import com.franco.moviesdb.network.remoteDatasourceTvAction.RemoteDatasourceTvActionImpl
import com.franco.moviesdb.network.remoteDatasourceTvComedy.RemoteDataSourceTvComedyImpl
import com.franco.moviesdb.repository.actorsRepository.ActorsRepository
import com.franco.moviesdb.repository.movieActionRepository.MovieActionRepository
import com.franco.moviesdb.repository.movieActionRepository.MovieActionRepositoryImpl
import com.franco.moviesdb.repository.movieComedyRepository.MovieComedyRepository
import com.franco.moviesdb.repository.movieComedyRepository.MovieComedyRepositoryImpl
import com.franco.moviesdb.repository.tvActionRepository.TvActionRepository
import com.franco.moviesdb.repository.tvActionRepository.TvActionRepositoryImpl
import com.franco.moviesdb.repository.tvComedyRepository.TvComedyRepository
import com.franco.moviesdb.repository.tvComedyRepository.TvComedyRepositoryImpl
import com.franco.moviesdb.ui.movie.action.MovieActionViewModel
import com.franco.moviesdb.ui.movie.comedy.MovieComedyViewModel
import com.franco.moviesdb.ui.movieDetails.DetailViewModel
import com.franco.moviesdb.util.BASE_URL
import com.franco.moviesdb.util.DATABASE_MOVIE_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    )
            .fallbackToDestructiveMigration()
            .build()


//    @Singleton
//    @Provides
//    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//    } else {
//        OkHttpClient
//                .Builder()
//                .build()
//    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
//        val client = OkHttpClient.Builder()
//                .build()
//        return Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//                .baseUrl(BASE_URL)
//                .client(client)
//            .build()
//            .create(ApiService::class.java)

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
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
    fun provideDao(db: MovieDatabase) = db.moviesActionDAO()

    @Singleton
    @Provides
    fun provideMovieActionRepository(
            localDatasourceMoviesAction: LocalDatasourceMoviesActionImpl,
            remoteDatasourceMoviesAction: RemoteDatasourceMovieActionImpl,

            ): MovieActionRepository {
        return MovieActionRepositoryImpl(localDatasourceMoviesAction, remoteDatasourceMoviesAction)
    }

    @Singleton
    @Provides
    fun providesRepoToMovieActionVm(repositoryImpl: MovieActionRepositoryImpl): MovieActionViewModel {
        return MovieActionViewModel(repositoryImpl)
    }

    @Singleton
    @Provides
    fun provideMovieComedyRepository(
            localDatasourceMoviesComedy: LocalDataSourceMovieComedyImpl,
            remoteDatasourceMoviesComedy: RemoteDatasourceMovieComedyImpl,

            ): MovieComedyRepository {
        return MovieComedyRepositoryImpl(localDatasourceMoviesComedy, remoteDatasourceMoviesComedy)
    }

    @Singleton
    @Provides
    fun provideTvActionRepository(
            localDatasourceTvAction: LocalDataSourceTvActionImpl,
            remoteDatasourceTvAction: RemoteDatasourceTvActionImpl,

            ): TvActionRepository {
        return TvActionRepositoryImpl(localDatasourceTvAction, remoteDatasourceTvAction)
    }

    @Singleton
    @Provides
    fun provideTvComedyRepository(
            localDatasourceTvComedy: LocalDataSourceTvComedyImpl,
            remoteDatasourceTvComedy: RemoteDataSourceTvComedyImpl,

            ): TvComedyRepository {
        return TvComedyRepositoryImpl(localDatasourceTvComedy, remoteDatasourceTvComedy)
    }

    @Singleton
    @Provides
    fun provideActorsRepository(
            localDatasourceActorsImpl: LocalDatasourceActorsImpl,
            remoteDatasourceActorsImpl: RemoteDatasourceActorsImpl
    ): ActorsRepository {
        return ActorsRepository(localDatasourceActorsImpl, remoteDatasourceActorsImpl)
    }


    @Singleton
    @Provides
    fun providesRepoToMovieComedyVm(movieComedyRepo: MovieComedyRepositoryImpl): MovieComedyViewModel {
        return MovieComedyViewModel(movieComedyRepo)
    }
//
//    fun providesRepoToTvActionVm(repositoryImpl: RepositoryImpl): TvActionViewModel {
//        return TvActionViewModel(repositoryImpl)
//    }
//
//    fun providesRepoToTvComedyVm(repositoryImpl: RepositoryImpl): TvComedyViewModel {
//        return TvComedyViewModel(repositoryImpl)
//    }

    @Singleton
    @Provides
    fun provideDetailViewModel(actorsRepo: ActorsRepository): DetailViewModel {
        return DetailViewModel(actorsRepo)
    }


//    @Singleton
//    @Provides
//    fun providesRepoToVm(service: ApiService): MovieActionViewModel {
//        return MovieActionViewModel(service)
//    }
}