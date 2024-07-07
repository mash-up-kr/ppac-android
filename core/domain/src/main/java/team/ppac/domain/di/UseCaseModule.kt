package team.ppac.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import team.ppac.domain.usecase.GetMemeUseCase
import team.ppac.domain.usecase.GetMemeUseCaseImpl
import team.ppac.domain.usecase.SampleUseCase
import team.ppac.domain.usecase.SampleUseCaseImpl
import team.ppac.domain.usecase.UserCreateUseCase
import team.ppac.domain.usecase.UserCreateUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindSampleUseCase(impl: SampleUseCaseImpl): SampleUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUserCreateUseCase(impl: UserCreateUseCaseImpl): UserCreateUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetMEmeUseCase(impl: GetMemeUseCaseImpl): GetMemeUseCase
}