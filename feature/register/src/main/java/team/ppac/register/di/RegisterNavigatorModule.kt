package team.ppac.register.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.ppac.navigator.RegisterNavigator
import team.ppac.register.navigator.RegisterNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
internal abstract class RegisterNavigatorModule {
    @Binds
    @ActivityScoped
    abstract fun bindRegisterNavigator(impl: RegisterNavigatorImpl): RegisterNavigator
}