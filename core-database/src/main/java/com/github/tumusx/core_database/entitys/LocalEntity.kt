package com.github.tumusx.core_database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local")
data class LocalEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long? = null,

    @ColumnInfo(name = "name_local")
    val nameLocal: String?,
)