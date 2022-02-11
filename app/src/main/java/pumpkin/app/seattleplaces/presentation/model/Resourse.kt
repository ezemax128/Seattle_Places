package pumpkin.app.seattleplaces.presentation.model

import retrofit2.Response
import java.lang.Exception

sealed class Resourse<out T>{
    class Loading<out T>: Resourse<T>()
    data class Success<out T>(val data: Response<Place>): Resourse<T>()
    data class Failure<out T>(val e: Exception): Resourse<T>()
}
