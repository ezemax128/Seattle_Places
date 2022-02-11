package pumpkin.app.seattleplaces.presentation.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: IconPlace
)

