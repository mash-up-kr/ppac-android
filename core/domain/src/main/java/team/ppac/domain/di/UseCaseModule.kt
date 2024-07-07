package team.ppac.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import team.ppac.domain.usecase.CreateUserUseCase
import team.ppac.domain.usecase.CreateUserUseCaseImpl
import team.ppac.domain.usecase.GetUserUseCase
import team.ppac.domain.usecase.GetUserUseCaseImpl
import team.ppac.domain.usecase.SampleUseCase
import team.ppac.domain.usecase.SampleUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindSampleUseCase(impl: SampleUseCaseImpl): SampleUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindCreateUserUseCase(impl: CreateUserUseCaseImpl): CreateUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserUseCase(impl: GetUserUseCaseImpl): GetUserUseCase
}