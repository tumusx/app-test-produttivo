package com.github.tumusx.core_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.tumusx.core_database.entitys.LocalEntity

@Dao
interface LocalDao {
    @Insert
    suspend fun insertItem(vararg entity: LocalEntity)

    @Query("SELECT * FROM local")
    suspend fun getLocalItems(): List<LocalEntity>

}