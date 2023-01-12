package com.github.tumusx.feature_equipment.data

import com.github.tumusx.core_database.database.AppDatabase
import com.github.tumusx.core_database.entitys.EquipmentEntity
import com.github.tumusx.core_database.entitys.LocalEntity
import com.github.tumusx.feature_equipment.domain.EquipmentDTO
import com.github.tumusx.feature_equipment.domain.IEquipmentRepository
import com.github.tumusx.feature_equipment.domain.LocalDTO
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(private val dataSource: AppDatabase) :
    IEquipmentRepository {

    override suspend fun getEquipmentItems(): List<EquipmentDTO>? {
        return try {
            val resultEquipmentEntity = dataSource.equipmentDAO().selectEquipmentItems()
            mapperToVo(resultEquipmentEntity)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    override suspend fun insertEquipmentItem(equipmentDTO: EquipmentDTO) {
        try {
            dataSource.equipmentDAO().insertEquipmentItems(mapperToEntityItem(equipmentDTO))
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override suspend fun updateEquipmentItem(equipmentDTO: EquipmentDTO) {
        try {
            dataSource.equipmentDAO().updateEquipmentItems(mapperToEntityItem(equipmentDTO))
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override fun mapperToEntityList(equipmentDTO: List<EquipmentDTO>): List<EquipmentEntity> {
        return equipmentDTO.map { equipmentVOItem ->
            EquipmentEntity(
                codeEquipment = equipmentVOItem.codeEquipment,
                localEquipment = LocalEntity(nameLocal = equipmentVOItem.localEquipment?.nameLocal),
                nameEquipment = equipmentVOItem.nameEquipment,
                markEquipment = equipmentVOItem.markEquipment,
                imageEquipment = equipmentVOItem.imageEquipment,
                typeEquipment = equipmentVOItem.typeEquipment,
                observation = equipmentVOItem.observation
            )
        }
    }

    override fun mapperToVo(equipmentEntity: List<EquipmentEntity>): List<EquipmentDTO> {
        return equipmentEntity.map { equipmentItemEntity ->
            EquipmentDTO(
                idItem = equipmentItemEntity.equipmentId,
                codeEquipment = equipmentItemEntity.codeEquipment,
                localEquipment = LocalDTO(nameLocal = equipmentItemEntity.localEquipment.nameLocal),
                nameEquipment = equipmentItemEntity.nameEquipment,
                markEquipment = equipmentItemEntity.markEquipment,
                imageEquipment = equipmentItemEntity.imageEquipment,
                typeEquipment = equipmentItemEntity.typeEquipment,
                observation = equipmentItemEntity.observation
            )
        }
    }

    override fun mapperToEntityItem(equipmentDTO: EquipmentDTO): EquipmentEntity {
        return EquipmentEntity(
            equipmentId = equipmentDTO.idItem,
            codeEquipment = equipmentDTO.codeEquipment,
            localEquipment = LocalEntity(nameLocal = equipmentDTO.localEquipment?.nameLocal),
            nameEquipment = equipmentDTO.nameEquipment,
            markEquipment = equipmentDTO.markEquipment,
            imageEquipment = equipmentDTO.imageEquipment,
            typeEquipment = equipmentDTO.typeEquipment,
            observation = equipmentDTO.observation
        )
    }
}