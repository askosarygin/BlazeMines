package com.example.game_ui.screen_fragment_module

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.common.BlazeMinesFragment
import com.example.game_ui.R
import com.example.game_ui.di.GameComponentViewModel

class FragmentModuleGame : BlazeMinesFragment(R.layout.fragment_module_game) {
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<GameComponentViewModel>()

        val screenDestination = arguments?.getString(
            resources.getString(
                com.example.common.R.string.deep_link_argument_name_screen_destination
            ),
            ""
        )!!

        findNavController().navigate(
            Uri.parse(
                buildString {
                    append(resources.getString(com.example.common.R.string.deep_link_game_module_screen))
                    append(screenDestination)
                }
            )
        )

        super.onAttach(context)
    }
}