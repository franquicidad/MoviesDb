package com.franco.moviesdb.repository.actorsBioRepository

import android.util.Log
import androidx.lifecycle.LiveData
import com.franco.moviesdb.database.actorsBiographyLocal.ActorBioLocalDatasourceImpl
import com.franco.moviesdb.domain.Actor
import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.network.actorsBiographyRemote.ActorBioRemoteDatasourceImpl
import com.franco.moviesdb.util.NetworkUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class actorBioRepositoryImpl @Inject constructor(
        private val actorsBioLocalDatasource: ActorBioLocalDatasourceImpl,
        private val actorsBioRemoteDatasource: ActorBioRemoteDatasourceImpl,
        private val isInternetAvailable: NetworkUtils
) : actorBioRepository {
    override suspend fun getActorBioFromDb(actorId: Int): ActorBiographyResponce {
        return actorsBioLocalDatasource.getActorInfo(actorId)
    }

    override suspend fun retreiveAndAddToDatabase(actorId: Int) {
        if (isInternetAvailable.isInternetAvailable()) {
            val actor = actorsBioRemoteDatasource.getActorBiographyRemote(actorId)
            actorsBioLocalDatasource.insertActorToDb(actor)
        }
    }
}