package com.franco.moviesdb.ui.actorsDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.repository.actorsBioRepository.actorBioRepository
import kotlinx.coroutines.flow.Flow

class ActorsDetailViewModel @ViewModelInject constructor(
    private val actorBioRepository: actorBioRepository
) : ViewModel() {

    suspend fun getActorBioFromDatabase(actorId: Int): Flow<ActorBiographyResponce> =
        actorBioRepository.getActorBioFromDb(actorId)

    suspend fun addActorToDatabase(actorId: Int) =
        actorBioRepository.retreiveAndAddToDatabase(actorId)


}