package com.github.tumusx.core_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.tumusx.core_database.entitys.EquipmentEntity

@Dao
interface EquipmentDao {

    @Query("SELECT * FROM equipment")
    suspend fun selectEquipmentItems() : List<EquipmentEntity>

    @Insert
    suspend fun insertEquipmentItems(vararg equipmentEntity: EquipmentEntity)

    @Update
    suspend fun updateEquipmentItems(vararg equipmentEntity: EquipmentEntity)
}