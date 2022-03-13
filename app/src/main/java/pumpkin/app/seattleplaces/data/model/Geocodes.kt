package pumpkin.app.seattleplaces.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geocodes(
    @SerializedName("main")
    val geoMain: GeoMain
):Parcelable
