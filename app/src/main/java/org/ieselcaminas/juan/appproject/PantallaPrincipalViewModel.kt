package org.ieselcaminas.juan.appproject

import androidx.lifecycle.*
import kotlinx.coroutines.Job


class PantallaPrincipalViewModel() : ViewModel() {

    private var viewModelJob = Job()

    val firebaseDataRepository = FirebaseDataRepository()

    //está función nos dará la información de firebase
    fun fetchUserData(): LiveData<MutableList<Place>>{
        val mutableData = MutableLiveData<MutableList<Place>>()

        //estara siempre esscuchando hasta que nos devuelva una lista de lugares
        firebaseDataRepository.getPlaceData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    private val _navigateToPlaceInfo = MutableLiveData<String>()
    val navigateToPlaceInfo
        get() = _navigateToPlaceInfo

    fun onPlaceClicked(id: String) {
        _navigateToPlaceInfo.value = id
    }

    fun onPlaceInfoNavigated() {
        _navigateToPlaceInfo.value = null
    }


}