package com.github.tumusx.feature_equipment.presenter.common

import com.github.tumusx.feature_equipment.domain.EquipmentDTO

class ValidateSearchList(private val equipmentList: List<EquipmentDTO>) {

    fun searchListEquipment(typesInput: String): List<EquipmentDTO> {
        val searchResult = mutableListOf<EquipmentDTO>()
        for (itemEquipmentList in equipmentList) {
            if (itemEquipmentList.codeEquipment.toString().lowercase()
                    .contains(typesInput.lowercase()) || (itemEquipmentList.localEquipment?.nameLocal?.lowercase()?.contains(
                    typesInput.lowercase()
                ) == true) || itemEquipmentList.nameEquipment.lowercase().contains(typesInput.lowercase())
            ) {
                searchResult.add(itemEquipmentList)
                return searchResult
            }
        }
        return emptyList()
    }
}