package com.example.settings_ui.screen_fragment_module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.common.BlazeMinesFragment
import com.example.settings_ui.R
import com.example.settings_ui.di.SettingsComponentViewModel

class FragmentModuleSettings : BlazeMinesFragment(R.layout.fragment_module_settings) {
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<SettingsComponentViewModel>()

        super.onAttach(context)
    }
}