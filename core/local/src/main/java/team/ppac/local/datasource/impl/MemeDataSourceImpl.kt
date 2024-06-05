package team.ppac.local.datasource.impl

import kotlinx.coroutines.flow.Flow
import team.ppac.local.dao.MemeDao
import team.ppac.local.datasource.MemeDataSource
import team.ppac.local.entity.MemeEntity
import javax.inject.Inject

internal class MemeDataSourceImpl @Inject constructor(
    private val memeDao: MemeDao
) : MemeDataSource {
    override fun getMemes(): Flow<List<MemeEntity>> {
        return memeDao.getMemes()
    }
}
