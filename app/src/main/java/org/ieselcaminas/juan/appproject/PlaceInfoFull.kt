package org.ieselcaminas.juan.appproject

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class PlaceInfoFull : Fragment() {

    companion object {
        fun newInstance() = PlaceInfoFull()
    }

    private lateinit var viewModel: PlaceInfoFullViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_info_full_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlaceInfoFullViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
