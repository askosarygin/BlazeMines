package com.example.settings_ui.screen_change_background

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.settings_ui.R
import com.example.settings_ui.databinding.FragmentScreenChangeBackgroundBinding
import com.example.settings_ui.di.SettingsComponentViewModel
import javax.inject.Inject

class FragmentScreenChangeBackground : BlazeMinesFragment(R.layout.fragment_screen_change_background) {
    private lateinit var binding: FragmentScreenChangeBackgroundBinding

    @Inject
    lateinit var factory: ViewModelScreenChangeBackground.Factory

    private val viewModel by viewModels<ViewModelScreenChangeBackground> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenChangeBackgroundBinding.inflate(
            inflater,
            container,
            false
        )

        SettingsComponentViewModel.getComponent().inject(this)

        initCollect()

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.buttonBackPressed()
        }
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenChangeBackground.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings ->
                            navigateToActionId(R.id.action_fragmentScreenChangeBackground_to_fragmentScreenSettings)

                    }
                }
            }
        }
    }
}