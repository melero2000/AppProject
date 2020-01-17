package org.ieselcaminas.juan.appproject.PlaceFullData

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.ieselcaminas.juan.appproject.R
import org.ieselcaminas.juan.appproject.databinding.PlaceInfoFullFragmentBinding


class PlaceInfoFull : Fragment() {

    lateinit var binding: PlaceInfoFullFragmentBinding

    private lateinit var viewModel: PlaceInfoFullViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.place_info_full_fragment, container, false)

        val placeId = arguments?.getString("placeKey")

        binding.placeIdTextview.text = placeId

        Toast.makeText(context, placeId, Toast.LENGTH_SHORT).show()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlaceInfoFullViewModel::class.java)
    }

}
