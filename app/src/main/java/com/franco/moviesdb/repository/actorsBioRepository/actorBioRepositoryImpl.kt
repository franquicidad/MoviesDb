package com.franco.moviesdb.repository.actorsBioRepository

import android.util.Log
import androidx.lifecycle.LiveData
import com.franco.moviesdb.database.actorsBiographyLocal.ActorBioLocalDatasourceImpl
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.network.actorsBiographyRemote.ActorBioRemoteDatasourceImpl
import kotlinx.coroutines.flow.Flow

class actorBioRepositoryImpl(
    private val actorsBioLocalDatasource: ActorBioLocalDatasourceImpl,
    private val actorsBioRemoteDatasource: ActorBioRemoteDatasourceImpl
) : actorBioRepository {
    override suspend fun getActorBioFromDb(actorId: Int): Flow<ActorBiographyResponce> {
        return actorsBioLocalDatasource.getActorInfo(actorId)
    }

    override suspend fun retreiveAndAddToDatabase(actorId: Int) {
        val list= actorsBioRemoteDatasource.getActorBiographyRemote(actorId)
        Log.i("Bio","$list")
        val actor = actorsBioRemoteDatasource.getActorBiographyRemote(actorId)
        actorsBioLocalDatasource.insertActorToDb(actor)
    }
}