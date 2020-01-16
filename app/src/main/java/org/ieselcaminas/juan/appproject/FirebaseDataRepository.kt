package org.ieselcaminas.juan.appproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class FirebaseDataRepository {

    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference

    //busca todos los datos en firebase, los mete a la lista y los devuelve
    fun getPlaceData(): LiveData<MutableList<Place>>{

        val mutableData = MutableLiveData<MutableList<Place>>()
        var finalImageUrl: String = ""
        var descargada = false

        db.collection("places").get().addOnSuccessListener {allPlaces ->
            val listData = mutableListOf<Place>()
            for(document: QueryDocumentSnapshot in allPlaces){

                val imageNom = document.getDocumentReference("imageURL").id
              //  Log.i("ImageURL", imageUrl)
                val refFinal = "/places_images/"+imageNom


                storageRef.child(refFinal).downloadUrl.addOnSuccessListener {
                        Log.i("ImageURL", "Be: " + it)
                        finalImageUrl = it.toString()
                        Log.i("ImageURL", "Dins: " + finalImageUrl)


                }.addOnFailureListener {
                    Log.i("ImageURL", "Error: "+ it)

                }


                val id = document.id
                val name = document.getString("name")
                val location = document.getString("location")
                val place = Place(id, name!!, "", location!!)
                listData.add(place)


                Log.i("ImageURL", "Fora: "+ finalImageUrl)

            }

            mutableData.value = listData
        }
        return mutableData
    }

}