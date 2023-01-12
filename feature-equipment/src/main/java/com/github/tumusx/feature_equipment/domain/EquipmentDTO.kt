package com.github.tumusx.feature_equipment.domain

data class EquipmentDTO(
    var idItem: Long? = null,
    var codeEquipment: Long,
    var localEquipment: LocalDTO?,
    var nameEquipment: String,
    var markEquipment: String,
    var imageEquipment: String?,
    var typeEquipment: String,
    var observation: String?
)