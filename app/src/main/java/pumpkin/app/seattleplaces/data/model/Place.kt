package pumpkin.app.seattleplaces.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    @SerializedName("results")
    val meta: List<PlaceData>
):Parcelable




