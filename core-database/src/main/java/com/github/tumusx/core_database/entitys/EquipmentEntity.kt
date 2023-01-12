package com.github.tumusx.core_database.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class EquipmentEntity(
    @PrimaryKey(autoGenerate = true)
    var equipmentId: Long? = null,
    @ColumnInfo(name = "code_equipment")
    val codeEquipment: Long,

    @ColumnInfo(name = "local_equipment")
    val localEquipment: LocalEntity,

    @ColumnInfo(name = "name_equipment")
    val nameEquipment: String,

    @ColumnInfo(name = "mark_equipment")
    val markEquipment: String,

    @ColumnInfo(name = "image_equipment")
    val imageEquipment: String?,

    @ColumnInfo(name = "type_equipment")
    val typeEquipment: String,

    @ColumnInfo(name = "observation")
    val observation: String?
)