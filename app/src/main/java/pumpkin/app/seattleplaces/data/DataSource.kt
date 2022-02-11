package pumpkin.app.seattleplaces.data


import pumpkin.app.seattleplaces.presentation.model.Place
import pumpkin.app.seattleplaces.presentation.model.Resourse
import retrofit2.Response

class DataSource {
    suspend fun getPlacesData(query: String): Resourse<Response<Place>> {
        return Resourse.Success(
            RetrofitClient.webService.getPlaces(
                query,
                "seattle",
                "50",
                "application/json",
                "fsq38thYuHy5j1j88ZrBj8+77Gecg3Y0f1jIvipUxSNoRls="
            )
        )
    }
}