package com.franco.moviesdb.ui.actorsDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.franco.moviesdb.domain.ActorBiographyResponce
import com.franco.moviesdb.repository.actorsBioRepository.actorBioRepository
import kotlinx.coroutines.flow.Flow

class ActorsDetailViewModel @ViewModelInject constructor(
    private val actorBioRepository: actorBioRepository
) : ViewModel() {

    suspend fun getActorBioFromDatabase(actorId: Int): Flow<ActorBiographyResponce> {
        return actorBioRepository.getActorBioFromDb(actorId)

    }

    suspend fun addActorToDatabase(actorId: Int) =
        actorBioRepository.retreiveAndAddToDatabase(actorId)

    private val _actorName = MutableLiveData<ActorBiographyResponce>()
    val actorName : LiveData<ActorBiographyResponce> get() =_actorName

    private val _actorsBirth = MutableLiveData<ActorBiographyResponce>()
    val actorsBirth : LiveData <ActorBiographyResponce> get() =_actorsBirth

    private val _actorBirthLocation = MutableLiveData<ActorBiographyResponce>()
    val actorBirthLocation : LiveData <ActorBiographyResponce> get() =_actorBirthLocation

    private val _biography = MutableLiveData<ActorBiographyResponce>()
    val biography : LiveData <ActorBiographyResponce> get() =_biography

    private val _homepage = MutableLiveData<ActorBiographyResponce>()
    val homepage : LiveData <ActorBiographyResponce> get() =_homepage

}