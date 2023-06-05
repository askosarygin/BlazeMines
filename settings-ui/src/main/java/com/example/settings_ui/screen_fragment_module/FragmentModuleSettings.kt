package com.example.settings_ui.screen_fragment_module

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.common.BlazeMinesFragment
import com.example.settings_ui.R
import com.example.settings_ui.di.SettingsComponentViewModel

class FragmentModuleSettings : BlazeMinesFragment(R.layout.fragment_module_settings) {
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<SettingsComponentViewModel>()

        val screenDestination = arguments?.getString(
            resources.getString(
                com.example.common.R.string.deep_link_argument_name_screen_destination
            ),
            ""
        )!!

        findNavController().navigate(
            Uri.parse(
                buildString {
                    append(resources.getString(com.example.common.R.string.deep_link_settings_module_screen))
                    append(screenDestination)
                }
            )
        )

        super.onAttach(context)
    }
}