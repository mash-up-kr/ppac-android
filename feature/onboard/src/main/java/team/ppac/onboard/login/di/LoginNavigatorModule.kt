package team.ppac.onboard.login.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.ppac.navigator.LoginNavigator
import team.ppac.onboard.login.navigator.LoginNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
internal abstract class LoginNavigatorModule {
    @Binds
    @ActivityScoped
    abstract fun bindLoginNavigator(impl: LoginNavigatorImpl): LoginNavigator
}