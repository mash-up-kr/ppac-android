package team.ppac.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.ppac.remote.datasource.SampleDataSource
import team.ppac.remote.datasource.impl.SampleDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindSampleDataSource(impl: SampleDataSourceImpl): SampleDataSource
}