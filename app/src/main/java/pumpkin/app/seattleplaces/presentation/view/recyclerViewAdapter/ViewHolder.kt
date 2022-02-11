package pumpkin.app.seattleplaces.presentation.view.recyclerViewAdapter


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import pumpkin.app.seattleplaces.presentation.model.PlaceData
import pumpkin.app.seattleplaces.databinding.ItemRowBinding


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @SuppressLint("SetTextI18n")
    fun bind(item: PlaceData, clickDetector: (bundle: Bundle) -> Unit) {
        val iconPrefix: String = item.category[item.category.lastIndex].icon.prefix
        val iconSuffix: String = item.category[item.category.lastIndex].icon.suffix
        val ICON_SIZE = 120
        val icon: String = iconPrefix + ICON_SIZE + iconSuffix
        val neighborhood: String = when (item.address._neighborhood.isNullOrEmpty()) {
            true -> {
                ""
            }
            false -> {
                item.address._neighborhood[0]
            }
        }
        //binding the item row layout
        val binding: ItemRowBinding = ItemRowBinding.bind(itemView)

        //assign values at the different views
        binding.namePlace.text = item.name
        binding.categoryPlace.text = item.category[0].name
        binding.iconPlace.load(icon)
        binding.neighborhoodPlace.text =
            "$neighborhood, ${item.address._locality}, ${item.address._country}"
        binding.address.text = "Address: ${item.address._address}"

        itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.let {
                it.putDouble("lat", item.geocodes.geoMain.latitude)
                it.putDouble("long", item.geocodes.geoMain.longitude)
                it.putString("name", item.name)
                it.putString("category", item.category[0].name)
                it.putString("distance", item.distance.toString())
                it.putString("address", "Address: ${item.address._address}")
            }
            clickDetector(bundle)
        }

    }
}
