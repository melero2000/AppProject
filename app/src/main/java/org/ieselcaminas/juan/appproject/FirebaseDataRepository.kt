package org.ieselcaminas.juan.appproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener




class FirebaseDataRepository {

    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()
    var storageRef = storage.reference



    //busca todos los datos en firebase, los mete a la lista y los devuelve
    fun getPlaceData(): LiveData<MutableList<Place>>{
        val mutableData = MutableLiveData<MutableList<Place>>()
        db.collection("places").get().addOnSuccessListener {allPlaces ->
            val listData = mutableListOf<Place>()
            for(document: QueryDocumentSnapshot in allPlaces){

                val imageUrl = document.getDocumentReference("imageURL").path

                //esto da un error.
                //val imageUrlDownload = storageRef.child(imageUrl).storage.reference.downloadUrl.result

                Log.i("ImageURL", ""+ imageUrl)


                val name = document.getString("name")
                val location = document.getString("location")
                val place = Place(name!!, imageUrl, location!!)
                listData.add(place)
            }

              mutableData.value = listData
        }
        return mutableData
    }

}