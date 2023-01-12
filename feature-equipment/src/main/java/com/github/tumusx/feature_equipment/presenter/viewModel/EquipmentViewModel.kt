package com.github.tumusx.feature_equipment.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_equipment.di.IoDispatcher
import com.github.tumusx.feature_equipment.domain.IEquipmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val equipmentRepository: IEquipmentRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) :
    ViewModel() {
    private val _stateUI: MutableLiveData<StateUI> = MutableLiveData(StateUI.IsLoading)
    val stateUI: LiveData<StateUI> = _stateUI

    init {
        listItem()
    }

    fun listItem() {
        viewModelScope.launch(coroutineDispatcher) {
            val response = equipmentRepository.getEquipmentItems()
            try {
                if (response == null) {
                    _stateUI.postValue(StateUI.EmptyListError)
                    return@launch
                }
                if (response.isNotEmpty()) {
                    response.let { equipmentsItems ->
                        _stateUI.postValue(StateUI.Success(equipmentsItems))
                    }
                } else {
                    _stateUI.postValue(StateUI.EmptyListError)
                }
            } catch (exception: Exception) {
                _stateUI.postValue(StateUI.Error("Ops... Aconteceu um erro inesperado"))
                exception.printStackTrace()
            }
        }
    }
}