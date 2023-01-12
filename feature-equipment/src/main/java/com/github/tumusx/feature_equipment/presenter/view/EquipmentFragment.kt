package com.github.tumusx.feature_equipment.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.tumusx.common_design_system.R
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_equipment.databinding.FragmentEquipmentBinding
import com.github.tumusx.feature_equipment.domain.EquipmentDTO
import com.github.tumusx.feature_equipment.presenter.adapter.EquipmentAdapter
import com.github.tumusx.feature_equipment.presenter.common.UpdateListEquipments
import com.github.tumusx.feature_equipment.presenter.common.ValidateSearchList
import com.github.tumusx.feature_equipment.presenter.common.messageErrorSnackBar
import com.github.tumusx.feature_equipment.presenter.view.bottomSheet.CreateOrUpdateBottomSheet
import com.github.tumusx.feature_equipment.presenter.viewModel.EquipmentViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EquipmentFragment : Fragment() {
    private lateinit var binding: FragmentEquipmentBinding
    private val viewModel: EquipmentViewModel by viewModels()
    private lateinit var equipmentAdapter: EquipmentAdapter
    private var equipmentList: List<EquipmentDTO>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEquipmentBinding.inflate(layoutInflater)
        configureListeners()
        configureObservables()
        onConfigureSearchView()
        updateListByViewModel()
        return binding.root
    }

    private fun setListEquipment(equipments: List<EquipmentDTO>){
        equipmentAdapter.updateList(equipments)
    }

    private fun configureAdapterItems(equipmentDTO: List<EquipmentDTO>) {
        equipmentAdapter = EquipmentAdapter { itemEquipment ->
            CreateOrUpdateBottomSheet.newInstance(itemEquipment, true)
                .show(childFragmentManager, CreateOrUpdateBottomSheet::class.java.name)
        }
        setListEquipment(equipmentDTO)
        binding.rvContainer.adapter = equipmentAdapter
    }

    private fun configureObservables() {
        viewModel.stateUI.observe(viewLifecycleOwner) { state ->
            stateUI(state)
        }
    }

    private fun stateUI(stateUI: StateUI) {
        val visibilityProgressBar = when (stateUI) {
            is StateUI.Success<*> -> {
                equipmentList = stateUI.data as List<EquipmentDTO>
                equipmentList?.let {
                configureAdapterItems(it) }
                View.GONE
            }

            is StateUI.Error -> {
                binding.root.messageErrorSnackBar()
                View.GONE
            }

            is StateUI.IsLoading -> {
                View.VISIBLE
            }

            is StateUI.EmptyListError -> {
                binding.llEmptyListContainer.root.visibility = View.VISIBLE
                binding.searchView.visibility = View.GONE
                View.GONE
            }
        }
        binding.progressBarCircular.visibility = visibilityProgressBar
    }

    private fun updateListByViewModel() {
        UpdateListEquipments.instance(object : UpdateListEquipments {
            override fun onSuccessUpdateList() {
                viewModel.listItem()
            }
        })
    }

    private fun onConfigureSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchListValidate(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchListValidate(it) }
                return false
            }
        })
    }

    private fun searchListValidate(typeInput: String) {
        val searchItemValidate = equipmentList?.let { ValidateSearchList(it) }
        val equipmentsList = searchItemValidate?.searchListEquipment(typeInput)
        if (equipmentsList?.isNotEmpty() == true) {
            setListEquipment(equipmentsList)
        } else {
            viewLifecycleOwner.lifecycleScope.launch {
                delay(400)
                Snackbar.make(binding.root, getString(R.string.nenhum_item_encontrado),Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun showBottomSheet(){
         CreateOrUpdateBottomSheet().show(
            childFragmentManager,
            CreateOrUpdateBottomSheet::class.java.name
        )
    }

    private fun configureListeners() {

        binding.imgCreateNewEquipment.setOnClickListener { showBottomSheet()}

        binding.llEmptyListContainer.btnOk.setOnClickListener {
            showBottomSheet()
        }
    }

}