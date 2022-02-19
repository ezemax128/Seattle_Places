package pumpkin.app.seattleplaces.data.repository

import pumpkin.app.seattleplaces.data.model.Place
import pumpkin.app.seattleplaces.data.model.Resourse
import retrofit2.Response

interface Repository {
    suspend fun getDataPlace(query: String): Resourse<Response<Place>>
}