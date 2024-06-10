package team.ppac.local.datasource

import kotlinx.coroutines.flow.Flow
import team.ppac.local.entity.MemeEntity

interface MemeDataSource {
    fun getMemes(): Flow<List<MemeEntity>>
}
