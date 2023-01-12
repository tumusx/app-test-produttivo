package com.github.tumusx.feature_equipment.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.tumusx.feature_equipment.databinding.EquipmentContainerItemsBinding
import com.github.tumusx.feature_equipment.domain.EquipmentDTO
import com.github.tumusx.feature_equipment.util.ImageUtils

class EquipmentAdapter(private val callBack: (EquipmentDTO) -> Unit) : RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>() {
    private var equipmentItems = emptyList<EquipmentDTO>()

    class EquipmentViewHolder(val binding: EquipmentContainerItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun configureUI(equipment: EquipmentDTO) {
            binding.imgEquipment.setImageBitmap(
                ImageUtils().decodeImage(
                    equipment.imageEquipment ?: "",
                    binding.root.context
                )
            )
            binding.txtNameEquipment.text = equipment.nameEquipment
            binding.txtCodeEquipment.text = equipment.codeEquipment.toString()
            binding.txtTypeEquipment.text = equipment.typeEquipment
            binding.txtNameEquipment.text = equipment.localEquipment?.nameLocal ?: "N/A"
            binding.txtObservationEquipment.text = equipment.observation ?: "N/A"
            binding.txtMarkEquipment.text = equipment.markEquipment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder =
        EquipmentViewHolder(EquipmentContainerItemsBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        holder.configureUI(equipmentItems[position])
        holder.binding.root.setOnClickListener{callBack.invoke(equipmentItems[position])}
    }

    fun updateList(newList: List<EquipmentDTO>) {
        val diffUtilDispatcher = DiffUtil.calculateDiff(CommonDiffUtil(newList, equipmentItems))
        equipmentItems = newList
        diffUtilDispatcher.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = equipmentItems.size
}