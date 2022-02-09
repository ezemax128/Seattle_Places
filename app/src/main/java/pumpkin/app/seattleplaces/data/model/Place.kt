package pumpkin.app.seattleplaces.data.model

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("results")
    val meta: List<PlaceData>
)




