package team.ppac.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.ppac.remote.datasource.MemeDataSource
import team.ppac.remote.datasource.SampleDataSource
import team.ppac.remote.datasource.UserRemoteDataSource
import team.ppac.remote.datasource.impl.MemeDataSourceImpl
import team.ppac.remote.datasource.impl.SampleDataSourceImpl
import team.ppac.remote.datasource.impl.UserDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    @Singleton
    fun bindSampleDataSource(impl: SampleDataSourceImpl): SampleDataSource

    @Binds
    @Singleton
    fun bindUserDataSource(impl: UserDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    fun bindMemeDataSource(impl: MemeDataSourceImpl):  MemeDataSource
}