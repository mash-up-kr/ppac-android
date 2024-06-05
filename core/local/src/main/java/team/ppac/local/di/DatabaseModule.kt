package team.ppac.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import team.ppac.local.db.FarmemeDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FarmemeDatabase =
        Room.databaseBuilder(
            context = context,
            klass = FarmemeDatabase::class.java,
            name = FarmemeDatabase.DATABASE_NAME
        ).build()
}
