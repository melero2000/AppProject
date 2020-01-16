package org.ieselcaminas.juan.appproject

import androidx.lifecycle.*
import kotlinx.coroutines.Job


class PantallaPrincipalViewModel() : ViewModel() {

    private var viewModelJob = Job()

   /* var db = FirebaseFirestore.getInstance()

    var coleccionRef =  db.collection("places")

    var lugaresRef = coleccionRef.get().addOnCompleteListener {  }


    //CON ESTO SE COGE EL SITIO DE LA BBDD---aqui la coleccion...........---esto es el ID.....
    var docRef = db.collection("places").document("poQWbys9ZizJkUf00pdo").get().addOnSuccessListener { document ->
        if (document != null) {                                      //y para coger el campo especifico así!! .get("nombreDelCampo")
            Log.d("ViewModel", "DocumentSnapshot data: ${document.data?.get("nombre")}")
        } else {
            Log.d("ViewModel", "No such document")
        }
    }
        .addOnFailureListener { exception ->
            Log.d("ViewModel", "get failed with ", exception)
        }
*/
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


//    lateinit var placesLiveData: MutableLiveData<MutableList<DocumentSnapshot>>

  /*  var places = db.collection("places").get().result?.documents
        set(value) {
            placesLiveData.value = value
        }*/


}