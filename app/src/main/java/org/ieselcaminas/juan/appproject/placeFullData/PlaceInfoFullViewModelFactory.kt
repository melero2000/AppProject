package org.ieselcaminas.juan.appproject.placeFullData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlaceInfoFullViewModelFactory(
    private val placeKey: String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceInfoFullViewModel::class.java)) {
            return PlaceInfoFullViewModel(placeKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}