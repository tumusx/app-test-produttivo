package com.github.tumusx.feature_local.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.tumusx.common_design_system.R
import com.github.tumusx.common_util.state.StateUI
import com.github.tumusx.feature_local.databinding.FragmentLocalBinding
import com.github.tumusx.feature_local.domain.LocalVO
import com.github.tumusx.feature_local.presenter.viewModel.LocalViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalFragment : Fragment() {
    private lateinit var binding: FragmentLocalBinding
    private val viewModel: LocalViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocalBinding.inflate(layoutInflater)
        configureListeners()
        configureObservables()
        return binding.root
    }

    private fun configureObservables() {
        viewModel.stateUI.observe(viewLifecycleOwner) { state ->
            stateUI(state)
        }
    }

    private fun stateUI(stateUI: StateUI) {
        when (stateUI) {
            is StateUI.Success<*> -> {
                Toast.makeText(requireContext(), "Local cadastrado com sucesso", Toast.LENGTH_LONG)
                    .show()
            }

            is StateUI.Error -> {
                Toast.makeText(requireContext(), stateUI.messageError, Toast.LENGTH_LONG).show()
            }

            is StateUI.IsLoading -> {
            }
            else -> {
                Toast.makeText(
                    requireContext(),
                    "Erro desconhecido ao cadastrar",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }


    private fun configureListeners() {
        binding.btnOk.setOnClickListener {
            if (binding.txtNameLocal.text?.isEmpty() == true || binding.txtNameLocal.text == null) {
                Toast.makeText(
                    requireContext(),
                    "É preciso informar os campos destacados com *",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            viewModel.insertLocal(LocalVO(binding.txtNameLocal.text.toString()))
        }
    }
}