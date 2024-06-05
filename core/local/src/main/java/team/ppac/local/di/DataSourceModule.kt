package team.ppac.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.ppac.local.datasource.MemeDataSource
import team.ppac.local.datasource.impl.MemeDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsMemeDataSource(dataSource: MemeDataSourceImpl): MemeDataSource
}