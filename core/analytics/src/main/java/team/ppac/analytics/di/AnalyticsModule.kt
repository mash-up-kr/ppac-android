package team.ppac.analytics.di

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.ppac.analytics.AnalyticsHelper
import team.ppac.analytics.AnalyticsHelperImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AnalyticsModule {

    @Binds
    @Singleton
    abstract fun bindAnalyticsHelper(impl: AnalyticsHelperImpl): AnalyticsHelper

    companion object {
        @Provides
        @Singleton
        fun provideAnalytics() = Firebase.analytics
    }
}