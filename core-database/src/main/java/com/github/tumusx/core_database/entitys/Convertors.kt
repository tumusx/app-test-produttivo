package com.github.tumusx.core_database.entitys

import androidx.room.TypeConverter
import com.google.gson.Gson

class Convertors {

    @TypeConverter
    fun listToJson(value: List<EquipmentEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toJsonLocalEntity(value: LocalEntity): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToLocalEntity(value : String) : LocalEntity {
       return Gson().fromJson(value, LocalEntity::class.java) as LocalEntity
    }

    @TypeConverter
    fun jsonToList(value: String): List<EquipmentEntity> {
        val contactListArray =
            Gson().fromJson(value, Array<EquipmentEntity>::class.java) as Array<EquipmentEntity>
        return contactListArray.toList()
    }
}