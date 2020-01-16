package org.ieselcaminas.juan.appproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.places_item.view.*
import org.ieselcaminas.juan.appproject.databinding.PantallaPrincipalBinding
import org.ieselcaminas.juan.appproject.databinding.PlacesItemBinding


class PlacesListAdapter(val context: Context): ListAdapter<Place, PlacesListAdapter.MainViewHolder>(SleepNightDiffCallback()) {

    private var dataListPlaces = mutableListOf<Place>()

    private lateinit var binding: PantallaPrincipalBinding

    fun setListDataPlaces(data: MutableList<Place>){
        dataListPlaces = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    class MainViewHolder(binding: PlacesItemBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PlacesItemBinding.inflate(layoutInflater, parent, false)
                return MainViewHolder(binding)
            }
        }
        fun bindView(place: Place) {
            //Glide es una libreria que carga las URL de las imagenes directamente en el ImageView así podremos trabajar muy facilmente con imagenes y firebase
            Glide.with(itemView).load(place.imageURL).into(itemView.place_photo)
            itemView.place_location_info.text = place.location
            itemView.place_name.text = place.name

        }

    }


    class SleepNightDiffCallback : DiffUtil.ItemCallback<Place>() {

        //compara los identificadores para ver si son el mismo item o no, si tienen el mismo id return true sino false
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.name == newItem.name
        }

        //compara el contenido de los items, si es el mismo return true sino false
        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem // como es un dataclass comparara campo a campo
        }

    }

}





