package pumpkin.app.seattleplaces.data


import pumpkin.app.seattleplaces.data.model.Place
import pumpkin.app.seattleplaces.data.model.Resourse
import retrofit2.Response
import javax.inject.Inject

class DataSource @Inject constructor(private val webService: WebService) {
    suspend fun getPlacesData(query: String): Resourse<Response<Place>> {
        return Resourse.Success(
            webService.getPlaces(
                query,
                "seattle",
                "50",
                "application/json",
                "fsq38thYuHy5j1j88ZrBj8+77Gecg3Y0f1jIvipUxSNoRls="
            )
        )
    }
}