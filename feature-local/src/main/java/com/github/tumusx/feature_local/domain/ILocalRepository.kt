package com.github.tumusx.feature_local.domain

interface ILocalRepository {
    suspend fun createLocal(localVo: LocalVO)
}