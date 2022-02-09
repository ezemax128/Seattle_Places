package pumpkin.app.seattleplaces.data.model

import com.google.gson.annotations.SerializedName

data class IconPlace(
    @SerializedName("prefix")
    val prefix: String,
    @SerializedName("suffix")
    val suffix: String
)

