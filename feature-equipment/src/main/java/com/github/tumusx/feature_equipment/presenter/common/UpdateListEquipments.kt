package com.github.tumusx.feature_equipment.presenter.common

interface UpdateListEquipments {
    fun onSuccessUpdateList()

    companion object {
        lateinit var instanceList: UpdateListEquipments
        fun instance(updateListEquipments: UpdateListEquipments) {
            instanceList = updateListEquipments
        }
    }
}