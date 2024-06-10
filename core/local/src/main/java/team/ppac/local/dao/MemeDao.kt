package team.ppac.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import team.ppac.local.entity.MemeEntity

@Dao
internal interface MemeDao : BaseDao<MemeEntity> {
    @Query("SELECT * FROM memes")
    fun getMemes(): Flow<List<MemeEntity>>
}
