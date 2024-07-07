package team.ppac.onboard.login.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.ppac.navigation.navigator.MainNavigatorImpl
import team.ppac.navigator.MainNavigator

@Module
@InstallIn(ActivityComponent::class)
internal abstract class MainNavigatorModule {
    @Binds
    @ActivityScoped
    abstract fun bindMainNavigator(impl: MainNavigatorImpl): MainNavigator
}