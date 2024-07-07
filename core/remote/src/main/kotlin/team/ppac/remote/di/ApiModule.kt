package team.ppac.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import team.ppac.remote.api.MemeApi
import team.ppac.remote.api.SampleApi
import team.ppac.remote.api.UserApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {

    @Provides
    @Singleton
    fun provideSampleApi(retrofit: Retrofit): SampleApi = retrofit.create()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create()

    @Provides
    @Singleton
    fun provideMemeApi(retrofit: Retrofit): MemeApi = retrofit.create()
}