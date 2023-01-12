package com.github.tumusx.feature_local.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_local.di.IoDispatcher
import com.github.tumusx.feature_local.domain.ILocalRepository
import com.github.tumusx.feature_local.domain.LocalVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    private val localRepository: ILocalRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _stateUI: MutableLiveData<StateUI> = MutableLiveData(StateUI.IsLoading)
    val stateUI: LiveData<StateUI> = _stateUI

     fun insertLocal(localVO: LocalVO) {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                localRepository.createLocal(localVO)
                _stateUI.postValue(StateUI.Success(true))
            }catch (exception: Exception) {
                _stateUI.postValue(StateUI.Error("Aconteceu um erro inesperado."))
                exception.printStackTrace()
            }
        }
    }
}