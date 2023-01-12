package com.github.tumusx.feature_equipment.repository

import com.github.tumusx.core_database.entitys.EquipmentEntity
import com.github.tumusx.core_database.entitys.LocalEntity
import com.github.tumusx.feature_equipment.domain.EquipmentDTO
import com.github.tumusx.feature_equipment.domain.IEquipmentRepository
import com.github.tumusx.feature_equipment.domain.LocalDTO

class EquipmentFakeRepository : IEquipmentRepository {
    var equipmentDTO: List<EquipmentDTO>? = listOf(
        EquipmentDTO(
            12, 12, LocalDTO("IFG"), "TV Plasma", "Consul",
            "base/64", "Televisão", "Tv está estragada"
        )
    )

    override suspend fun getEquipmentItems(): List<EquipmentDTO>? {
        return equipmentDTO
    }

    override suspend fun insertEquipmentItem(equipmentDTO: EquipmentDTO) {}

    override suspend fun updateEquipmentItem(equipmentDTO: EquipmentDTO) {}

    override fun mapperToEntityList(equipmentDTO: List<EquipmentDTO>): List<EquipmentEntity> =
        emptyList()

    override fun mapperToVo(equipmentEntity: List<EquipmentEntity>): List<EquipmentDTO> =
        emptyList()

    override fun mapperToEntityItem(equipmentDTO: EquipmentDTO): EquipmentEntity = EquipmentEntity(
        12, 12, LocalEntity(1, "IFG"), "TV Plasma", "Consul",
        "base/64", "Televisão", "Tv está estragada"
    )
}