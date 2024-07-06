package team.ppac.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.ppac.local.datasource.MemeDataSource
import team.ppac.local.datasource.UserLocalDataSource
import team.ppac.local.datasource.impl.MemeDataSourceImpl
import team.ppac.local.datasource.impl.UserDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsMemeDataSource(dataSource: MemeDataSourceImpl): MemeDataSource

    @Binds
    @Singleton
    abstract fun bindsUserDataSource(dataSource: UserDataSourceImpl): UserLocalDataSource
}
