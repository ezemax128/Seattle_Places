package pumpkin.app.seattleplaces.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceData(
    @SerializedName("fsq_id")
    val id: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val address: Location,
    @SerializedName("categories")
    val category: List<Category>,
    @SerializedName("geocodes")
    val geocodes: Geocodes

):Parcelable