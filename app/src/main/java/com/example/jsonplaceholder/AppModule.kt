package com.example.jsonplaceholder

import com.example.jsonplaceholder.mainRepository.MainRepository
import com.example.jsonplaceholder.network.ApiInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public class AppModule {
    @Provides
    @Singleton
    fun provideMainRepository(): MainRepository = MainRepository()

}

@Module
@DisableInstallInCheck
public interface BindModule {
    @Binds
    fun bindSettings(apiInterface: ApiInterface?): ApiInterface?
}