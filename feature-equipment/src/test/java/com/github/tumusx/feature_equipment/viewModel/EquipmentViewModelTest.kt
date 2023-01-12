package com.github.tumusx.feature_equipment.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.tumusx.common_basetest.MainCoroutineRule
import com.github.tumusx.common_basetest.getOrAwaitValueTest
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_equipment.presenter.viewModel.EquipmentViewModel
import com.github.tumusx.feature_equipment.repository.EquipmentFakeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EquipmentViewModelTest {

    @get:Rule
    val instanceExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineElement = MainCoroutineRule()

    private lateinit var viewModel: EquipmentViewModel
    private val equipmentFakeRepository = EquipmentFakeRepository()

    @Before
    fun setup() {
        viewModel = EquipmentViewModel(equipmentFakeRepository, Dispatchers.Main)
    }

    @Test
    fun `when getListEquipmentDTO return list`() {
        viewModel.listItem()
        val result = viewModel.stateUI.getOrAwaitValueTest()
        assertEquals(result, StateUI.Success(equipmentFakeRepository.equipmentDTO))
    }

    @Test
    fun `when call getListEquipment return emptyList stateUI`() {
        equipmentFakeRepository.equipmentDTO = emptyList()
        viewModel.listItem()
        val result = viewModel.stateUI.getOrAwaitValueTest()
        assertEquals(result, StateUI.EmptyListError)
    }

    @Test
    fun `when call getListEquipment, but null list, return emptyList error`() {
        equipmentFakeRepository.equipmentDTO = null
        viewModel.listItem()
        val result = viewModel.stateUI.getOrAwaitValueTest()
        assertEquals(result, StateUI.EmptyListError)
    }
}