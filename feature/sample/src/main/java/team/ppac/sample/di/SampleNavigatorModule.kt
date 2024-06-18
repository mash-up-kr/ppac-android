package team.ppac.sample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import team.ppac.navigator.SampleNavigator
import team.ppac.sample.navigator.SampleNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
internal abstract class SampleNavigatorModule {

    @Binds
    @ActivityScoped
    abstract fun bindSampleNavigator(impl: SampleNavigatorImpl): SampleNavigator
}