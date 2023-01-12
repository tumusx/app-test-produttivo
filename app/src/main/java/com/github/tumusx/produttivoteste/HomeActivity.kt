package com.github.tumusx.produttivoteste

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.tumusx.common_design_system.R
import com.github.tumusx.feature_equipment.presenter.view.EquipmentFragment
import com.github.tumusx.feature_local.presenter.view.LocalFragment
import com.github.tumusx.produttivoteste.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        configureToolBarHome()
        setupAppBarConfiguration()
        configureTransactionFragment(EquipmentFragment())
        setContentView(binding.root)
    }

    private fun configureTransactionFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, fragment, fragment.tag)
            .commitAllowingStateLoss()
    }

    private fun setupAppBarConfiguration() {
        binding.bottomNavigationBar.setOnItemSelectedListener { itemMenu ->
            val fragmentSelect = if (itemMenu.itemId == R.id.equipments) {
                EquipmentFragment()
            } else {
                LocalFragment()
            }
            itemMenu.isChecked = true
            configureTransactionFragment(fragmentSelect)
            configureToolBarHome(itemMenu.title.toString())
            false
        }
    }

    private fun configureToolBarHome(tittleToolbar: String = getString(R.string.equipment)) {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = tittleToolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

}