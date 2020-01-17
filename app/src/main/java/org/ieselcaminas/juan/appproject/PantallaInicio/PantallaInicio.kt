package org.ieselcaminas.juan.appproject.PantallaInicio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import org.ieselcaminas.juan.appproject.R
import org.ieselcaminas.juan.appproject.databinding.PantallaInicioBinding


/**
 * A simple [Fragment] subclass.
 */
class PantallaInicio : Fragment() {

    companion object {
        const val TAG = "MainFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    private lateinit var binding: PantallaInicioBinding


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.pantalla_inicio, container, false)

        binding.buttonSingin.setOnClickListener {
         //   launchSignInFlow()
            navegarPantallaInicioToPantallaPrincipal()

        }


        return binding.root

    }

    fun navegarPantallaInicioToPantallaPrincipal() {
        view?.findNavController()?.navigate(R.id.action_pantallaInicio_to_pantallaPrincipal)

    }


    //este metodo sera igual siempre que sea el sign in por correo y contraseña o con google
    //Este metodo es para lanzar el activity con los botones de inicar sesion, EL ACTIVITY LO CREA LA LIBRERIA ASI QUE NOSOTROS NO LO TENEMOS Q CREAR
    private fun launchSignInFlow() {
        //los providers so iguales siempre y cuando sean la utentificacion con email y el builder
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),
            SIGN_IN_RESULT_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in user.
                Log.i(
                    TAG,"Successfully signed in user " +
                            "${FirebaseAuth.getInstance().currentUser?.displayName}!" )
                navegarPantallaInicioToPantallaPrincipal()

            } else {
                // Sign in failed. If response is null the user canceled the sign-in flow using
                // the back button. Otherwise check response.getError().getErrorCode() and handle
                // the error.
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }


}
