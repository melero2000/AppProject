package org.ieselcaminas.juan.appproject

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.storage.StorageReference
import java.io.File


//Le asigno datos a los atributos por si luego me fuera necesario crear un constructor vacio
data class Place(var id : String, var name: String  = "NAME_DEFAULT", var imageURL: String, var location: String  = "LOCATION_DEFAULT")