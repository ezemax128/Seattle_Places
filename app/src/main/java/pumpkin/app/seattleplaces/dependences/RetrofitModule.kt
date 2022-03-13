package pumpkin.app.seattleplaces.dependences

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pumpkin.app.seattleplaces.data.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun retrofitProvider(): WebService =
        Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v3/places/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)

}