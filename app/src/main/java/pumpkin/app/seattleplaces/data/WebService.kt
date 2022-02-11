package pumpkin.app.seattleplaces.data


import pumpkin.app.seattleplaces.presentation.model.Place
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface WebService {
    @GET("search?")

    suspend fun getPlaces(
        @Query("query") query: String,
        @Query("near") PLACE: String,
        @Query("limit") LIMIT: String,
        @Header("Accept") HEADER: String,
        @Header("Authorization") ApiKey: String): Response<Place>
}