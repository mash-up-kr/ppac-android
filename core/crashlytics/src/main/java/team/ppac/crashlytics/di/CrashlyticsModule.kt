package team.ppac.crashlytics.di

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.ppac.crashlytics.logger.CrashlyticsLoggerTree
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CrashlyticsModule {

    @Binds
    @Singleton
    abstract fun bindCrashlyticsLoggerTree(tree: CrashlyticsLoggerTree): Timber.Tree

    companion object {
        @Provides
        @Singleton
        fun provideCrashlytics() = Firebase.crashlytics
    }
}