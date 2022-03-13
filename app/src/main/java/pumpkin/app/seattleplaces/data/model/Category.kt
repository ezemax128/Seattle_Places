package pumpkin.app.seattleplaces.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: IconPlace
):Parcelable

