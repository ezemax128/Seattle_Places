package pumpkin.app.seattleplaces.presentation.model

import com.google.gson.annotations.SerializedName


data class GeoMain(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
