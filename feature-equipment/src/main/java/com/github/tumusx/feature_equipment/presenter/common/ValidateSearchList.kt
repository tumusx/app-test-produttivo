package com.github.tumusx.feature_equipment.presenter.common

import com.github.tumusx.feature_equipment.domain.EquipmentDTO

class ValidateSearchList(private val equipmentList: List<EquipmentDTO>) {

    fun searchListEquipment(typesInput: String): List<EquipmentDTO> {
        val searchResult = mutableListOf<EquipmentDTO>()
        for (itemEquipmentList in equipmentList) {
            if (itemEquipmentList.codeEquipment.toString()
                    .contains(typesInput) || (itemEquipmentList.localEquipment?.nameLocal?.contains(
                    typesInput
                ) == true) || itemEquipmentList.nameEquipment.contains(typesInput)
            ) {
                searchResult.add(itemEquipmentList)
            }
        }
        return emptyList()
    }
}