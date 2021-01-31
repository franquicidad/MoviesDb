package com.franco.moviesdb.di

import android.accounts.NetworkErrorException
import android.content.Context
import androidx.room.Room
import com.franco.moviesdb.database.MovieDatabase
import com.franco.moviesdb.database.actors.actorListMovie.localDatasourceListMoviesByActor.LocalDatasourceMovieByActorImpl
import com.franco.moviesdb.database.actors.localDatasourceActors.LocalDatasourceActorsImpl
import com.franco.moviesdb.database.actors.remoteDatasourceActors.RemoteDatasourceActorsImpl
import com.franco.moviesdb.database.actorsBiographyLocal.ActorBioLocalDatasource
import com.franco.moviesdb.database.actorsBiographyLocal.ActorBioLocalDatasourceImpl
import com.franco.moviesdb.database.localDatasources.movies.localDataSourceMoviecomedy.LocalDataSourceMovieComedyImpl
import com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvAction.LocalDataSourceTvActionImpl
import com.franco.moviesdb.database.localDatasources.movies.localDatasourceTvComedy.LocalDataSourceTvComedyImpl
import com.franco.moviesdb.database.localDatasources.movies.localdatasourceMovieAction.LocalDatasourceMoviesActionImpl
import com.franco.moviesdb.database.similarMovies.localDatasourceSimilar.LocalDatasourceSimilarImpl
import com.franco.moviesdb.network.actorsBiographyRemote.ActorBioRemoteDatasource
import com.franco.moviesdb.network.actorsBiographyRemote.ActorBioRemoteDatasourceImpl
import com.franco.moviesdb.network.api.ApiService
import com.franco.moviesdb.network.remoteDatasourceMovieAction.RemoteDatasourceMovieActionImpl
import com.franco.moviesdb.network.remoteDatasourceMovieListByActor.RemoteDatasourceMovieListByActorImpl
import com.franco.moviesdb.network.remoteDatasourceMoviecomedy.RemoteDatasourceMovieComedyImpl
import com.franco.moviesdb.network.remoteDatasourceSimilar.RemoteDatasourceSimilarMoviesImpl
import com.franco.moviesdb.network.remoteDatasourceTvAction.RemoteDatasourceTvActionImpl
import com.franco.moviesdb.network.remoteDatasourceTvComedy.RemoteDataSourceTvComedyImpl
import com.franco.moviesdb.repository.actorsBioRepository.actorBioRepository
import com.franco.moviesdb.repository.actorsBioRepository.actorBioRepositoryImpl
import com.franco.moviesdb.repository.actorsMovieListRepository.ActorsMovieListRepository
import com.franco.moviesdb.repository.actorsMovieListRepository.ActorsMovieListRepositoryImpl
import com.franco.moviesdb.repository.actorsRepository.ActorsRepository
import com.franco.moviesdb.repository.movieActionRepository.MovieActionRepository
import com.franco.moviesdb.repository.movieActionRepository.MovieActionRepositoryImpl
import com.franco.moviesdb.repository.movieComedyRepository.MovieComedyRepository
import com.franco.moviesdb.repository.movieComedyRepository.MovieComedyRepositoryImpl
import com.franco.moviesdb.repository.similarRepository.SimilarRepository
import com.franco.moviesdb.repository.similarRepository.SimilarRepositoryImpl
import com.franco.moviesdb.repository.tvActionRepository.TvActionRepository
import com.franco.moviesdb.repository.tvActionRepository.TvActionRepositoryImpl
import com.franco.moviesdb.repository.tvComedyRepository.TvComedyRepository
import com.franco.moviesdb.repository.tvComedyRepository.TvComedyRepositoryImpl
import com.franco.moviesdb.ui.actorsDetail.ActorsDetailViewModel
import com.franco.moviesdb.ui.movie.action.MovieActionViewModel
import com.franco.moviesdb.ui.movie.comedy.MovieComedyViewModel
import com.franco.moviesdb.ui.movieDetails.DetailViewModel
import com.franco.moviesdb.ui.similarMoviesFragment.SimilarDetailViewModel
import com.franco.moviesdb.util.BASE_URL
import com.franco.moviesdb.util.DATABASE_MOVIE_NAME
import com.franco.moviesdb.util.NetworkUtils
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException
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




    @Singleton
    @Provides
    fun provideApiService(): ApiService {

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


//        catch (connection: ConnectException){}
//        catch (network: NetworkErrorException){}
//        catch (unknown: UnknownHostException){}

    }


    @Singleton
    @Provides
    fun provideDao(db: MovieDatabase) = db.moviesActionDAO()

    @Singleton
    @Provides
    fun provideMovieActionRepository(
            localDatasourceMoviesAction: LocalDatasourceMoviesActionImpl,
            remoteDatasourceMoviesAction: RemoteDatasourceMovieActionImpl,
            isInternetAvailable: NetworkUtils

    ): MovieActionRepository {
        return MovieActionRepositoryImpl(localDatasourceMoviesAction, remoteDatasourceMoviesAction, isInternetAvailable)
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
            isInternetAvailable: NetworkUtils

    ): MovieComedyRepository {
        return MovieComedyRepositoryImpl(localDatasourceMoviesComedy, remoteDatasourceMoviesComedy, isInternetAvailable)
    }

    @Singleton
    @Provides
    fun provideTvActionRepository(
            localDatasourceTvAction: LocalDataSourceTvActionImpl,
            remoteDatasourceTvAction: RemoteDatasourceTvActionImpl,
            isInternetAvailable: NetworkUtils

    ): TvActionRepository {
        return TvActionRepositoryImpl(localDatasourceTvAction, remoteDatasourceTvAction, isInternetAvailable)
    }

    @Singleton
    @Provides
    fun provideTvComedyRepository(
            localDatasourceTvComedy: LocalDataSourceTvComedyImpl,
            remoteDatasourceTvComedy: RemoteDataSourceTvComedyImpl,
            isInternetAvailable: NetworkUtils

    ): TvComedyRepository {
        return TvComedyRepositoryImpl(localDatasourceTvComedy, remoteDatasourceTvComedy, isInternetAvailable)
    }

    @Singleton
    @Provides
    fun provideActorsRepository(
            localDatasourceActorsImpl: LocalDatasourceActorsImpl,
            remoteDatasourceActorsImpl: RemoteDatasourceActorsImpl,
            isInternetAvailable: NetworkUtils

    ): ActorsRepository {
        return ActorsRepository(localDatasourceActorsImpl, remoteDatasourceActorsImpl, isInternetAvailable)
    }

    @Singleton
    @Provides
    fun provideActorBioRepository(
            actorsBioLocalDatasource: ActorBioLocalDatasourceImpl,
            actorsBioRemoteDatasource: ActorBioRemoteDatasourceImpl,
            isInternetAvailable: NetworkUtils
    ): actorBioRepository {
        return actorBioRepositoryImpl(actorsBioLocalDatasource, actorsBioRemoteDatasource, isInternetAvailable)
    }

    @Singleton
    @Provides
    fun provideSimilarRepository(
            localDatasourceSimilarImpl: LocalDatasourceSimilarImpl,
            remoteDatasourceSimilarImpl: RemoteDatasourceSimilarMoviesImpl,
            isInternetAvailable: NetworkUtils
    ): SimilarRepository {
        return SimilarRepositoryImpl(localDatasourceSimilarImpl, remoteDatasourceSimilarImpl, isInternetAvailable)
    }

    @Singleton
    @Provides
    fun provideMovieListByActorId(
            localDatasourceMovieByActor: LocalDatasourceMovieByActorImpl,
            remoteDatasourceMovieListByActor: RemoteDatasourceMovieListByActorImpl,
            isInternetAvailable: NetworkUtils
    ): ActorsMovieListRepository {
        return ActorsMovieListRepositoryImpl(localDatasourceMovieByActor, remoteDatasourceMovieListByActor, isInternetAvailable)
    }


    @Singleton
    @Provides
    fun providesRepoToMovieComedyVm(movieComedyRepo: MovieComedyRepositoryImpl, isInternet: NetworkUtils): MovieComedyViewModel {
        return MovieComedyViewModel(movieComedyRepo, isInternet)
    }

    @Singleton
    @Provides
    fun providesRepoToActorDetailVm(actorBioRepository: actorBioRepositoryImpl, actorMovieListRepo: ActorsMovieListRepositoryImpl): ActorsDetailViewModel {
        return ActorsDetailViewModel(actorBioRepository, actorMovieListRepo)
    }

    @Singleton
    @Provides
    fun provideRemoteDatasource(apiService: ApiService): RemoteDatasourceSimilarMoviesImpl {
        return RemoteDatasourceSimilarMoviesImpl(apiService)
    }


    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideDetailViewModel(
            actorsRepo: ActorsRepository,
            similarRepo: SimilarRepository
    ): DetailViewModel {
        return DetailViewModel(actorsRepo, similarRepository = similarRepo)
    }

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideSimilarDetailViewModel(
            actorsRepo: ActorsRepository,
            similarRepo: SimilarRepository
    ): SimilarDetailViewModel {
        return SimilarDetailViewModel(actorsRepo, similarRepository = similarRepo)
    }

    @Singleton
    @Provides
    fun provideActorsDetailViewModel(
            actorsBioRepo: actorBioRepositoryImpl,
            actorMovieListRepo: ActorsMovieListRepositoryImpl
    ): ActorsDetailViewModel {
        return ActorsDetailViewModel(actorsBioRepo, actorMovieListRepo)
    }

}