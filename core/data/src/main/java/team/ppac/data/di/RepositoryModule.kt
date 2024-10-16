package team.ppac.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.ppac.data.repository.KeywordRepositoryImpl
import team.ppac.data.repository.MemeRepositoryImpl
import team.ppac.data.repository.SampleRepositoryImpl
import team.ppac.data.repository.UserRepositoryImpl
import team.ppac.domain.repository.KeywordRepository
import team.ppac.domain.repository.MemeRepository
import team.ppac.domain.repository.SampleRepository
import team.ppac.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSampleRepository(impl: SampleRepositoryImpl): SampleRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindMemeRepository(impl: MemeRepositoryImpl): MemeRepository

    @Binds
    @Singleton
    abstract fun bindKeywordRepository(impl: KeywordRepositoryImpl): KeywordRepository
}