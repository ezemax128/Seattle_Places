package pumpkin.app.seattleplaces.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import pumpkin.app.seattleplaces.R
import pumpkin.app.seattleplaces.databinding.CustomInfoWindowsBinding

//This class represent the custom Windows inside the map
class CustomInfoWindowsAdapter(
    private val context: Context,
    private val name: String,
    private val category: String,
    private val distance: String,
    private val address: String
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker): View? {
        val customIW = LayoutInflater.from(context).inflate(R.layout.custom_info_windows, null)
        val binding = CustomInfoWindowsBinding.bind(customIW)
        binding.nameIW.text = name
        binding.categoryIW.text = category
        binding.addressIW.text = address
        binding.distanceIW.text = distance
        return customIW
    }

    override fun getInfoWindow(marker: Marker): View? = null
}