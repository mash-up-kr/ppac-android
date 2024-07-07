package team.ppac.local.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import team.ppac.local.datasource.AppConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppConfigModule {
    @Provides
    @Singleton
    fun provideAppConfig(
        @ApplicationContext context: Context,
    ) = object : AppConfig {
        @SuppressLint("HardwareIds")
        override val deviceId: String =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    }
}
