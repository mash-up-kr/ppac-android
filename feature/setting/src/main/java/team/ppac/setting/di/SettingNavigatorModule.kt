package team.ppac.setting.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.ppac.navigator.SettingNavigator
import team.ppac.setting.navigator.SettingNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
internal abstract class SettingNavigatorModule {
    @Binds
    @ActivityScoped
    abstract fun bindDetailNavigator(impl: SettingNavigatorImpl): SettingNavigator
}