package com.github.tumusx.feature_local.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.tumusx.feature_local.databinding.FragmentLocalBinding

class LocalFragment : Fragment() {
    private lateinit var binding: FragmentLocalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocalBinding.inflate(layoutInflater)
        return binding.root
    }
}