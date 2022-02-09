package pumpkin.app.seattleplaces.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import pumpkin.app.seattleplaces.R

//This class represent the custom Windows inside the map
class CustomInfoWindowsAdapter(
    private val context: Context,
    private val name: String,
    private val category: String,
    private val distance: String,
    private val address: String
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(p0: Marker): View? {
        val customIW = LayoutInflater.from(context).inflate(R.layout.custom_info_windows, null)
        customIW.findViewById<TextView>(R.id.nameIW).text = name
        customIW.findViewById<TextView>(R.id.categoryIW).text = category
        customIW.findViewById<TextView>(R.id.addressIW).text = address
        customIW.findViewById<TextView>(R.id.distanceIW).text = distance
        return customIW
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}