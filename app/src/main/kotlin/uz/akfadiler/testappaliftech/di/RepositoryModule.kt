package uz.akfadiler.testappaliftech.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.akfadiler.testappaliftech.data.repository.app.AppRepository
import uz.akfadiler.testappaliftech.data.repository.app.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository
}