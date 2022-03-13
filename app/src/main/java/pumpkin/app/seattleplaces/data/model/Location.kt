package pumpkin.app.seattleplaces.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @SerializedName("address")
    val _address: String,
    @SerializedName("locality")
    val _locality: String,
    @SerializedName("country")
    val _country: String,
    @SerializedName("neighborhood")
    val _neighborhood: List<String>
):Parcelable
