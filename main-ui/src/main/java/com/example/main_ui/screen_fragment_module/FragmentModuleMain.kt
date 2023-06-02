package com.example.main_ui.screen_fragment_module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.common.BlazeMinesFragment
import com.example.main_ui.R
import com.example.main_ui.di.MainComponentViewModel

class FragmentModuleMain : BlazeMinesFragment(R.layout.fragment_module_main) {
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainComponentViewModel>()

        super.onAttach(context)
    }
}