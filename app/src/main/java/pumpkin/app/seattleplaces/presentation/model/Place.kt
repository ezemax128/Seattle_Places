package pumpkin.app.seattleplaces.presentation.model

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("results")
    val meta: List<PlaceData>
)




