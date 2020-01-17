package org.ieselcaminas.juan.appproject.PantallaPrincipal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.ieselcaminas.juan.appproject.Place
import org.ieselcaminas.juan.appproject.databinding.PlacesItemBinding


class PlacesListAdapter(val clickListener: PlaceListener): ListAdapter<Place, PlacesListAdapter.MainViewHolder>(
    PlaceDiffCallback()
) {

    private var dataListPlaces = mutableListOf<Place>()

    fun setListDataPlaces(data: MutableList<Place>){
        dataListPlaces = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(
            parent
        )
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindView(getItem(position), clickListener)
    }


    class MainViewHolder(val binding: PlacesItemBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PlacesItemBinding.inflate(layoutInflater, parent, false)
                return MainViewHolder(
                    binding
                )
            }
        }
        fun bindView(place: Place, clickListener: PlaceListener) {
            binding.place = place
            //Glide es una libreria que carga las URL de las imagenes directamente en el ImageView así podremos trabajar muy facilmente con imagenes y firebase
            Glide.with(itemView).load(place.imageURL).into(binding.placePhoto)
            binding.placeLocationInfo.text = place.location
            binding.placeName.text = place.name
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }

    }


    class PlaceDiffCallback : DiffUtil.ItemCallback<Place>() {

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

class PlaceListener(val clickListener: (placeId: String) -> Unit){
    fun onClick(place: Place) = clickListener(place.id)
}





