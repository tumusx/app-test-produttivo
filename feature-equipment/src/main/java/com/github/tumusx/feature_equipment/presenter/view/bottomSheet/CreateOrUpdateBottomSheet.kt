package com.github.tumusx.feature_equipment.presenter.view.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.github.tumusx.common_design_system.R
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_equipment.databinding.FragmentCreateOrUpdateBottomSheetBinding
import com.github.tumusx.feature_equipment.domain.EquipmentDTO
import com.github.tumusx.feature_equipment.domain.LocalDTO
import com.github.tumusx.feature_equipment.presenter.common.UpdateListEquipments
import com.github.tumusx.feature_equipment.presenter.common.messageErrorSnackBar
import com.github.tumusx.feature_equipment.presenter.viewModel.CreateOrUpdateViewModel
import com.github.tumusx.feature_equipment.util.decodeImage
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateOrUpdateBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCreateOrUpdateBottomSheetBinding
    private var equipmentDTO: EquipmentDTO? = null
    private var isUpdateItem: Boolean = false
    private val viewModel: CreateOrUpdateViewModel by viewModels()
    private var isUpdateList = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateOrUpdateBottomSheetBinding.inflate(layoutInflater)
        updateUiEdit()
        setItemsEquipmentDTO()
        configureObservables()
        return binding.root
    }

    private fun setItemsEquipmentDTO() {
        binding.btnOk.setOnClickListener {
            val equipmentDTO = EquipmentDTO(
                idItem = equipmentDTO?.idItem,
                codeEquipment = binding.txtCodeNumber.text.toString().toLong(),
                markEquipment = binding.txtMakEquipment.text.toString(),
                nameEquipment = binding.txtNameEquipment.text.toString(),
                typeEquipment = binding.txtTypeEquipment.text.toString(),
                observation = binding.txtObservationEquipment.text.toString(),
                localEquipment = LocalDTO(nameLocal = binding.txtLocalEquipment.text.toString()),
                imageEquipment = "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"
            )
            if (isUpdateItem) {
                viewModel.updateEquipmentDTO(equipmentDTO)
            } else {
                viewModel.insertEquipment(equipmentDTO)
            }
        }
    }

    private fun configureObservables() {
        viewModel.stateUI.observe(viewLifecycleOwner) {
            stateUI(it)
        }
    }

    private fun configureMessageSuccess() {
        val message = if(isUpdateItem){
            getString(R.string.atualizado)
        }else{
            getString(R.string.cadastrado)
        }
        Toast.makeText(requireContext(), getString(R.string.success, message), Toast.LENGTH_LONG).show()
    }

    private fun stateUI(stateUI: StateUI) {
        when (stateUI) {
            is StateUI.Success<*> -> {
                configureMessageSuccess()
                isUpdateList = true
            }

            is StateUI.Error -> {
                binding.root.messageErrorSnackBar()
            }

            is StateUI.IsLoading -> {
            }
            else -> {
                binding.root.messageErrorSnackBar()
            }
        }
    }

    override fun onDestroy() {
        if (isUpdateList) UpdateListEquipments.instanceList.onSuccessUpdateList()
        super.onDestroy()
    }

    private fun updateUiEdit() {
        equipmentDTO?.let { equipment ->
            binding.imgItem.setImageBitmap(equipment.imageEquipment?.let { img ->
                decodeImage(
                    img,
                    requireContext()
                )
            })
            binding.txtCodeNumber.setText(equipment.codeEquipment.toString())
            binding.txtMakEquipment.setText(equipment.markEquipment)
            binding.txtNameEquipment.setText(equipment.nameEquipment)
            binding.txtTypeEquipment.setText(equipment.typeEquipment)
            binding.txtObservationEquipment.setText(equipment.observation ?: "N/A")
            binding.txtLocalEquipment.setText(equipment.localEquipment?.nameLocal ?: "N/A")
        }
    }


    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    companion object {
        fun newInstance(
            equipmentDTO: EquipmentDTO,
            isUpdateItem: Boolean
        ): CreateOrUpdateBottomSheet {
            val createOrUpdateBottomSheet = CreateOrUpdateBottomSheet()
            createOrUpdateBottomSheet.equipmentDTO = equipmentDTO
            createOrUpdateBottomSheet.isUpdateItem = isUpdateItem
            return createOrUpdateBottomSheet
        }
    }
}