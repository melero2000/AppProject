package org.ieselcaminas.juan.appproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.ieselcaminas.juan.appproject.databinding.PantallaPrincipalBinding
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * A simple [Fragment] subclass.
 */
class PantallaPrincipal : Fragment() {

    lateinit var adapter: PlacesListAdapter
    lateinit var binding: PantallaPrincipalBinding

    //con el by lazy lo que conseguimos esque esta variable se inicialice cuando se vaya a usar y sea necesario
    private val viewModel by lazy { ViewModelProviders.of(this).get(PantallaPrincipalViewModel::class.java)}

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.pantalla_principal, container, false)

        adapter = PlacesListAdapter(context!!)
        binding.placesRecycler.layoutManager = LinearLayoutManager(context)
        binding.placesRecycler.adapter = adapter
        observeData()


        return binding.root

    }

    fun observeData(){
        binding.progressBar.visibility = View.VISIBLE//se pone antes de q muestre los datos
        viewModel.fetchUserData().observe(this, Observer {placesList ->
            binding.progressBar.visibility = View.INVISIBLE //aqui lo quitamos para que se vean los datos
            placesList?.let {
                adapter.submitList(placesList)
           //ESTO DE AQUI ABAJO DEBERIAMOS QUITARLO PORQUE EL ADAPTER ES UN RECYCLERLIST
                adapter.setListDataPlaces(placesList)
            //    adapter.notifyDataSetChanged()
            }
        })
    }



}
