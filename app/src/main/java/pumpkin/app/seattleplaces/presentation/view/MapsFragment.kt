package pumpkin.app.seattleplaces.presentation.view

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import pumpkin.app.seattleplaces.R
import pumpkin.app.seattleplaces.databinding.FragmentMapsBinding

class MapsFragment : Fragment(R.layout.fragment_maps) {
    private lateinit var binding: FragmentMapsBinding
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var name: String = ""
    private var category: String = ""
    private var address: String = ""
    private var distance: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let { data ->
            latitude = data.getDouble("lat")
            longitude = data.getDouble("long")
            name = data.getString("name").toString()
            category = data.getString("category").toString()
            address = data.getString("address").toString()
            distance = "Distance ${data.getString("distance")} Mts"
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val seattle = LatLng(latitude, longitude)
        googleMap.setInfoWindowAdapter(
            CustomInfoWindowsAdapter(
                requireContext(),
                name,
                category,
                distance,
                address
            )
        )
        googleMap.addMarker(MarkerOptions().position(seattle))?.showInfoWindow()
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(seattle))
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(seattle, 18f),
            4000, null
        )

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapsBinding.bind(view)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        val toolbar = binding.toolbarMapsFragment
        val navController = findNavController()
        val appConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appConfiguration)
        toolbar.title = "Map of Seattle"

    }


}