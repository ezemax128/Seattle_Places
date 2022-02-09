package pumpkin.app.seattleplaces.data.model

import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("address")
    val _address: String,
    @SerializedName("locality")
    val _locality: String,
    @SerializedName("country")
    val _country: String,
    @SerializedName("neighborhood")
    val _neighborhood: List<String>
)
