package pumpkin.app.seattleplaces.presentation.view.recyclerViewAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pumpkin.app.seattleplaces.presentation.model.PlaceData
import pumpkin.app.seattleplaces.R

class RecyclerAdapter(
    private val listPlaces: List<PlaceData>,
    private val clickDetector: (bundle: Bundle) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        listPlaces.forEach { println(it) }
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPlaces[position], clickDetector)
    }

    override fun getItemCount(): Int = listPlaces.size


}