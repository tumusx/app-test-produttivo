package com.github.tumusx.feature_equipment.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.tumusx.feature_equipment.databinding.FragmentEquipmentBinding
import com.github.tumusx.feature_equipment.presenter.view.bottomSheet.CreateOrUpdateBottomSheet

class EquipmentFragment : Fragment() {
    private lateinit var binding: FragmentEquipmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEquipmentBinding.inflate(layoutInflater)
        configureListeners()
        return binding.root
    }

    private fun configureListeners() {
        binding.llEmptyListContainer.btnOk.setOnClickListener {
            CreateOrUpdateBottomSheet.newInstance()
                .show(parentFragmentManager, CreateOrUpdateBottomSheet::class.java.name)
        }
    }

}