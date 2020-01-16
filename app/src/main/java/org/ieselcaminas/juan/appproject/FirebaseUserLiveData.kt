package org.ieselcaminas.juan.appproject

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.LiveData

//esta clase es un livedata que almacena un firebaseuser o null que sera el value
class FirebaseUserLiveData : LiveData<FirebaseUser?>() {
    private val firebaseAuth = FirebaseAuth.getInstance()


    //este callback se ejecuta cuando se autentifica el usuario.
    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        //cambia el valor de value que es cuando hacemos clase.value lo que nos devuelve
        value = firebaseAuth.currentUser

    }

    // When this object has an active observer, start observing the FirebaseAuth state to see if
    // there is currently a logged in user.
    override fun onActive() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    // When this object no longer has an active observer, stop observing the FirebaseAuth state to
    // prevent memory leaks.
    override fun onInactive() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}