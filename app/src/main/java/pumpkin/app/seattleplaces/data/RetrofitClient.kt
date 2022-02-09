package pumpkin.app.seattleplaces.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v3/places/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}