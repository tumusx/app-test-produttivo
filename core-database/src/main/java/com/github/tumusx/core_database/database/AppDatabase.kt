package com.github.tumusx.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.tumusx.core_database.dao.EquipmentDao
import com.github.tumusx.core_database.dao.LocalDao
import com.github.tumusx.core_database.entitys.Convertors
import com.github.tumusx.core_database.entitys.EquipmentEntity
import com.github.tumusx.core_database.entitys.LocalEntity

@Database(
    entities = [LocalEntity::class, EquipmentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun equipmentDAO(): EquipmentDao
    abstract fun localDao(): LocalDao
}