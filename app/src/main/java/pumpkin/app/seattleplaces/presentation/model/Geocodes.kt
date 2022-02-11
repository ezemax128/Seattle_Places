package pumpkin.app.seattleplaces.presentation.model

import com.google.gson.annotations.SerializedName

data class Geocodes(
    @SerializedName("main")
    val geoMain: GeoMain
)
