package com.franco.moviesdb.repository.actorsMovieListRepository

import com.franco.moviesdb.database.actors.actorListMovie.localDatasourceListMoviesByActor.LocalDatasourceMovieByActor
import com.franco.moviesdb.database.actors.actorListMovie.localDatasourceListMoviesByActor.LocalDatasourceMovieByActorImpl
import com.franco.moviesdb.domain.ActorListMovies
import com.franco.moviesdb.network.remoteDatasourceMovieListByActor.RemoteDatasourceMovieListByActorImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ActorsMovieListRepositoryImpl(
        private val localDatasourceMovieByActor: LocalDatasourceMovieByActorImpl,
        private val remoteDatasourceMovieListByActor: RemoteDatasourceMovieListByActorImpl
) : ActorsMovieListRepository {
    override suspend fun getAllMoviesByActorId(actorId: Int): Flow<List<ActorListMovies>> {
        return localDatasourceMovieByActor.getAllMoviesByActorId(actorId)

    }

    override suspend fun retreiveFromNetAddToDB(actorId: Int) {

        val list: List<ActorListMovies> = remoteDatasourceMovieListByActor.getMoviesListByActor(actorId)
        CoroutineScope(Dispatchers.IO).launch {
            list.forEach {
                it.actorId = actorId
            }
        }


        localDatasourceMovieByActor.insertActors(list)

    }
}