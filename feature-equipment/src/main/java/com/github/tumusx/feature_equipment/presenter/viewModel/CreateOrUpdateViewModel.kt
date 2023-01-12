package com.github.tumusx.feature_equipment.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_equipment.data.EquipmentRepositoryImpl
import com.github.tumusx.feature_equipment.di.IoDispatcher
import com.github.tumusx.feature_equipment.domain.EquipmentDTO
import com.github.tumusx.feature_equipment.domain.IEquipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrUpdateViewModel @Inject constructor(
    private val repositoryImpl: IEquipmentRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _stateUI: MutableLiveData<StateUI> = MutableLiveData(StateUI.IsLoading)
    val stateUI: LiveData<StateUI> = _stateUI

    fun insertEquipment(equipmentDTO: EquipmentDTO) {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                repositoryImpl.insertEquipmentItem(equipmentDTO)
                _stateUI.postValue(StateUI.Success(true))
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun updateEquipmentDTO(equipmentDTO: EquipmentDTO) {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                repositoryImpl.updateEquipmentItem(equipmentDTO)
                _stateUI.postValue(StateUI.Success(true))
            } catch (exception: Exception) {
                exception.printStackTrace()
                _stateUI.postValue(StateUI.Error("Ops... Aconteceu um erro inesperado"))
            }
        }
    }
}