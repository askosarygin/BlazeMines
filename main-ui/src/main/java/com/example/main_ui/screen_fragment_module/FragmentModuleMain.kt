package com.example.main_ui.screen_fragment_module

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.common.BlazeMinesFragment
import com.example.main_ui.R
import com.example.main_ui.di.MainComponentViewModel

class FragmentModuleMain : BlazeMinesFragment(R.layout.fragment_module_main) {
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainComponentViewModel>()

        val screenDestination = arguments?.getString(
            resources.getString(
                com.example.common.R.string.deep_link_argument_name_screen_destination
            ),
            ""
        )!!

        findNavController().navigate(
            Uri.parse(
                buildString {
                    append(resources.getString(com.example.common.R.string.deep_link_main_module_screen))
                    append(screenDestination)
                }
            )
        )

        super.onAttach(context)
    }
}