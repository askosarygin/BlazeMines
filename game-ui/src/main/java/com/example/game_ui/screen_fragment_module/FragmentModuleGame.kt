package com.example.game_ui.screen_fragment_module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.common.BlazeMinesFragment
import com.example.game_ui.R
import com.example.game_ui.di.GameComponentViewModel

class FragmentModuleGame : BlazeMinesFragment(R.layout.fragment_module_game) {
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<GameComponentViewModel>()

        super.onAttach(context)
    }
}