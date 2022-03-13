package pumpkin.app.seattleplaces.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pumpkin.app.seattleplaces.data.model.Place
import pumpkin.app.seattleplaces.data.model.Resourse
import pumpkin.app.seattleplaces.data.DataSource
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource) : Repository {
    override suspend fun getDataPlace(query: String): Resourse<Response<Place>> =
        withContext(Dispatchers.IO) {
            return@withContext dataSource.getPlacesData(query)
        }
}