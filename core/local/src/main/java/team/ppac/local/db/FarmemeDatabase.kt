package team.ppac.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import team.ppac.local.dao.MemeDao
import team.ppac.local.entity.MemeEntity

@Database(
    entities = [MemeEntity::class],
    version = 1,
    exportSchema = false, // TODO(EvergreenTree97) : 이후 true로 변경
)
internal abstract class FarmemeDatabase : RoomDatabase() {

    abstract fun memeDao(): MemeDao

    companion object {
        const val DATABASE_NAME = "Farmeme-database"
    }

}