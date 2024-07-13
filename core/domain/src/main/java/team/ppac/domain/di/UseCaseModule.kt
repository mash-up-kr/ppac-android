package team.ppac.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import team.ppac.domain.usecase.CreateUserUseCase
import team.ppac.domain.usecase.CreateUserUseCaseImpl
import team.ppac.domain.usecase.GetMemeUseCase
import team.ppac.domain.usecase.GetMemeUseCaseImpl
import team.ppac.domain.usecase.GetRecommendKeywordsUseCase
import team.ppac.domain.usecase.GetRecommendKeywordsUseCaseImpl
import team.ppac.domain.usecase.GetUserRecentMemesUseCase
import team.ppac.domain.usecase.GetUserRecentMemesUseCaseImpl
import team.ppac.domain.usecase.GetUserSavedMemesUseCase
import team.ppac.domain.usecase.GetUserSavedMemesUseCaseImpl
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

    @Binds
    @ViewModelScoped
    abstract fun bindGetMemeUseCase(impl: GetMemeUseCaseImpl): GetMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserSavedMemesUseCase(impl: GetUserSavedMemesUseCaseImpl): GetUserSavedMemesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserRecentMemesUseCase(impl: GetUserRecentMemesUseCaseImpl): GetUserRecentMemesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindRecommendKeywordsUseCase(impl: GetRecommendKeywordsUseCaseImpl): GetRecommendKeywordsUseCase
}