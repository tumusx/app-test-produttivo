package com.github.tumusx.common_util.state

sealed class StateUI{
    data class Success<T>(val data: T) : StateUI()
    data class Error(val messageError: String) : StateUI()
    object EmptyListError: StateUI()
    object IsLoading : StateUI()
}