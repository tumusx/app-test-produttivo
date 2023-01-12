package com.github.tumusx.feature_local.data

import com.github.tumusx.core_database.database.AppDatabase
import com.github.tumusx.core_database.entitys.LocalEntity
import com.github.tumusx.feature_local.domain.ILocalRepository
import com.github.tumusx.feature_local.domain.LocalVO
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) : ILocalRepository {

    override suspend fun createLocal(localVo: LocalVO) {
        appDatabase.localDao().insertItem(LocalEntity(nameLocal = localVo.nameLocalVO))
    }
}