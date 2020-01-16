package org.ieselcaminas.juan.appproject

import com.google.firebase.firestore.DocumentReference


//Le asigno datos a los atributos por si luego me fuera necesario crear un constructor vacio
data class Place(var name: String  = "NAME_DEFAULT", var imageURL: String , var location: String  = "LOCATION_DEFAULT")