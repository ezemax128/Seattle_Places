package pumpkin.app.seattleplaces.dependences

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pumpkin.app.seattleplaces.data.repository.Repository
import pumpkin.app.seattleplaces.data.repository.RepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun binder(repositoryImpl: RepositoryImpl): Repository
}