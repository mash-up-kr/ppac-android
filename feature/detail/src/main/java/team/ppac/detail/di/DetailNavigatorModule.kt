package team.ppac.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.ppac.detail.navigator.DetailNavigatorImpl
import team.ppac.navigator.DetailNavigator

@Module
@InstallIn(ActivityComponent::class)
internal abstract class DetailNavigatorModule {
    @Binds
    @ActivityScoped
    abstract fun bindDetailNavigator(impl: DetailNavigatorImpl) : DetailNavigator
}