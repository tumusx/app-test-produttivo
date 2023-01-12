package com.github.tumusx.feature_equipment.presenter.view.bottomSheet

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.viewModels
import com.github.tumusx.common_design_system.R
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_equipment.databinding.FragmentCreateOrUpdateBottomSheetBinding
import com.github.tumusx.feature_equipment.domain.EquipmentDTO
import com.github.tumusx.feature_equipment.domain.LocalDTO
import com.github.tumusx.feature_equipment.presenter.common.UpdateListEquipments
import com.github.tumusx.feature_equipment.presenter.common.messageErrorSnackBar
import com.github.tumusx.feature_equipment.presenter.viewModel.CreateOrUpdateViewModel
import com.github.tumusx.feature_equipment.util.ImageUtils
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
        selectImageFromGallery()
        return binding.root
    }

    private fun setItemsEquipmentDTO() {
        binding.btnOk.setOnClickListener {
            if (binding.txtCodeNumber.text.toString()
                    .isEmpty() || binding.txtMakEquipment.text?.isEmpty() == true || binding.txtNameEquipment.text?.isEmpty() == true || binding.txtTypeEquipment.text?.isEmpty() == true
            ) {
                Toast.makeText(
                    requireContext(),
                    "É preciso informar os campos destacados com *",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            val equipmentDTO = EquipmentDTO(
                idItem = equipmentDTO?.idItem,
                codeEquipment = binding.txtCodeNumber.text.toString().toLong(),
                markEquipment = binding.txtMakEquipment.text.toString(),
                nameEquipment = binding.txtNameEquipment.text.toString(),
                typeEquipment = binding.txtCodeNumber.text.toString(),
                observation = binding.txtObservationEquipment.text.toString(),
                localEquipment = LocalDTO(nameLocal = binding.txtLocalEquipment.text.toString()),
                imageEquipment = ImageUtils().encodeImage(binding.imgItem.drawToBitmap())
            )
            if (isUpdateItem) {
                viewModel.updateEquipmentDTO(equipmentDTO)
            } else {
                viewModel.insertEquipment(equipmentDTO)
            }
        }
    }

    private fun selectImageFromGallery() {
        binding.imgItem.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1000)
        }
    }

    private fun configureObservables() {
        viewModel.stateUI.observe(viewLifecycleOwner) {
            stateUI(it)
        }
    }

    private fun configureMessageSuccess() {
        val message = if (isUpdateItem) {
            getString(R.string.atualizado)
        } else {
            getString(R.string.cadastrado)
        }
        Toast.makeText(requireContext(), getString(R.string.success, message), Toast.LENGTH_LONG)
            .show()
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
                ImageUtils().decodeImage(
                    img,
                    requireContext()
                )
            })
            binding.txtCodeNumber.setText(equipment.codeEquipment.toString())
            binding.txtMakEquipment.setText(equipment.markEquipment)
            binding.txtNameEquipment.setText(equipment.nameEquipment)
            binding.txtCodeNumber.setText(equipment.typeEquipment)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == 1000) {
            val returnUri = data?.data
            val bitmapImage = MediaStore.Images.Media.getBitmap(context?.contentResolver, returnUri)
            binding.imgItem.setImageBitmap(bitmapImage)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}