package org.ieselcaminas.juan.appproject.firebaseLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map


class LoginViewModel : ViewModel() {


    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    //esto lo que hace es que cuando user cambie se cambiara la variable enum, para luego poner login o logout dependieno
    val authenticationState = FirebaseUserLiveData()
        .map { user ->

        if (user != null ){
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }

    }

    // TODO Create an authenticationState variable based off the FirebaseUserLiveData object. By
    //  creating this variable, other classes will be able to query for whether the user is logged
    //  in or not

}
