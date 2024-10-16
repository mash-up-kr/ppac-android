package team.ppac.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import team.ppac.domain.usecase.CreateUserUseCase
import team.ppac.domain.usecase.CreateUserUseCaseImpl
import team.ppac.domain.usecase.DeleteSavedMemeUseCase
import team.ppac.domain.usecase.DeleteSavedMemeUseCaseImpl
import team.ppac.domain.usecase.EmitRefreshEventUseCase
import team.ppac.domain.usecase.EmitRefreshEventUseCaseImpl
import team.ppac.domain.usecase.GetLevelUseCase
import team.ppac.domain.usecase.GetLevelUseCaseImpl
import team.ppac.domain.usecase.GetMemeUseCase
import team.ppac.domain.usecase.GetMemeUseCaseImpl
import team.ppac.domain.usecase.GetRecommendKeywordsUseCase
import team.ppac.domain.usecase.GetRecommendKeywordsUseCaseImpl
import team.ppac.domain.usecase.GetSearchMemeUseCase
import team.ppac.domain.usecase.GetSearchMemeUseCaseImpl
import team.ppac.domain.usecase.GetThisWeekRecommendMemesUseCase
import team.ppac.domain.usecase.GetThisWeekRecommendMemesUseCaseImpl
import team.ppac.domain.usecase.GetTopKeywordsUseCase
import team.ppac.domain.usecase.GetTopKeywordsUseCaseImpl
import team.ppac.domain.usecase.GetUserRecentMemesUseCase
import team.ppac.domain.usecase.GetUserRecentMemesUseCaseImpl
import team.ppac.domain.usecase.GetUserRegisteredMemesUseCase
import team.ppac.domain.usecase.GetUserRegisteredMemesUseCaseImpl
import team.ppac.domain.usecase.GetUserSavedMemesUseCase
import team.ppac.domain.usecase.GetUserSavedMemesUseCaseImpl
import team.ppac.domain.usecase.GetUserUseCase
import team.ppac.domain.usecase.GetUserUseCaseImpl
import team.ppac.domain.usecase.ReactMemeUseCase
import team.ppac.domain.usecase.ReactMemeUseCaseImpl
import team.ppac.domain.usecase.RefreshEventUseCase
import team.ppac.domain.usecase.RefreshEventUseCaseImpl
import team.ppac.domain.usecase.SampleUseCase
import team.ppac.domain.usecase.SampleUseCaseImpl
import team.ppac.domain.usecase.SaveMemeUseCase
import team.ppac.domain.usecase.SaveMemeUseCaseImpl
import team.ppac.domain.usecase.SearchMemeUseCase
import team.ppac.domain.usecase.SearchMemeUseCaseImpl
import team.ppac.domain.usecase.SetLevelUseCase
import team.ppac.domain.usecase.SetLevelUseCaseImpl
import team.ppac.domain.usecase.ShareMemeUseCase
import team.ppac.domain.usecase.ShareMemeUseCaseImpl
import team.ppac.domain.usecase.UploadMemeUseCase
import team.ppac.domain.usecase.UploadMemeUseCaseImpl
import team.ppac.domain.usecase.WatchMemeUseCase
import team.ppac.domain.usecase.WatchMemeUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindSampleUseCase(impl: SampleUseCaseImpl): SampleUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetLastSeenMemeCountUseCase(impl: GetThisWeekRecommendMemesUseCaseImpl): GetThisWeekRecommendMemesUseCase

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
    abstract fun bindGetUserRegisteredMemesUseCase(impl: GetUserRegisteredMemesUseCaseImpl): GetUserRegisteredMemesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetUserRecentMemesUseCase(impl: GetUserRecentMemesUseCaseImpl): GetUserRecentMemesUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindRecommendKeywordsUseCase(impl: GetRecommendKeywordsUseCaseImpl): GetRecommendKeywordsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetTopKeywordsUseCase(impl: GetTopKeywordsUseCaseImpl): GetTopKeywordsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSaveMemeUseCase(impl: SaveMemeUseCaseImpl): SaveMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteSavedMemeUseCase(impl: DeleteSavedMemeUseCaseImpl): DeleteSavedMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSearchMemeUseCase(impl: GetSearchMemeUseCaseImpl): GetSearchMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindReactMemeUseCase(impl: ReactMemeUseCaseImpl): ReactMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindWatchMemeUseCase(impl: WatchMemeUseCaseImpl): WatchMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSetLevelUseCase(impl: SetLevelUseCaseImpl): SetLevelUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetLevelUseCase(impl: GetLevelUseCaseImpl): GetLevelUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindRefreshEventUseCase(impl: RefreshEventUseCaseImpl): RefreshEventUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindEmitRefreshEventUseCase(impl: EmitRefreshEventUseCaseImpl): EmitRefreshEventUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindShareMemeUseCase(impl: ShareMemeUseCaseImpl): ShareMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUploadMemeUseCase(impl: UploadMemeUseCaseImpl): UploadMemeUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSearchMemeUseCase(impl: SearchMemeUseCaseImpl): SearchMemeUseCase
}