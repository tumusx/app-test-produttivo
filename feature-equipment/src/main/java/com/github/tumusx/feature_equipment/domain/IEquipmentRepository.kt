package com.github.tumusx.feature_equipment.domain

import com.github.tumusx.core_database.entitys.EquipmentEntity

interface IEquipmentRepository {
    suspend fun getEquipmentItems(): List<EquipmentDTO>?
    suspend fun insertEquipmentItem(equipmentDTO: EquipmentDTO)
    suspend fun updateEquipmentItem(equipmentDTO: EquipmentDTO)
    fun mapperToEntityList(equipmentDTO: List<EquipmentDTO>): List<EquipmentEntity>
    fun mapperToVo(equipmentEntity: List<EquipmentEntity>): List<EquipmentDTO>
    fun mapperToEntityItem(equipmentDTO: EquipmentDTO): EquipmentEntity
}