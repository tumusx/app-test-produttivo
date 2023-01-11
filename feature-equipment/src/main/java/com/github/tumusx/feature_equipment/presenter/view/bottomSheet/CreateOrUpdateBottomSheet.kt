package com.github.tumusx.feature_equipment.presenter.view.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tumusx.common_design_system.R
import com.github.tumusx.feature_equipment.databinding.FragmentCreateOrUpdateBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateOrUpdateBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCreateOrUpdateBottomSheetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentCreateOrUpdateBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    companion object {
        fun newInstance(): CreateOrUpdateBottomSheet {
            return CreateOrUpdateBottomSheet()
        }
    }
}