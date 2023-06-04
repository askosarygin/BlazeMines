package com.example.settings_ui.screen_settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.common.ModuleNames
import com.example.common.NavHostsInfo
import com.example.settings_ui.R
import com.example.settings_ui.databinding.FragmentScreenSettingsBinding
import com.example.settings_ui.di.SettingsComponentViewModel
import javax.inject.Inject

class FragmentScreenSettings : BlazeMinesFragment(R.layout.fragment_screen_settings) {
    private lateinit var binding: FragmentScreenSettingsBinding

    @Inject
    lateinit var factory: ViewModelScreenSettings.Factory

    @Inject
    lateinit var navHostsInfo: NavHostsInfo

    private val viewModel by viewModels<ViewModelScreenSettings> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenSettingsBinding.inflate(
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
        binding.btnBackground.setOnClickListener {
            viewModel.buttonBackgroundPressed()
        }
        binding.btnCards.setOnClickListener {
            viewModel.buttonCardsPressed()
        }
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenSettings.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels ->
                            navigateToModule(
                                ModuleNames.Main,
                                navHostsInfo.globalNavHostId
                            )
                        ViewModelScreenSettings.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenChangeBackground ->
                            navigateToActionId(R.id.action_fragmentScreenSettings_to_fragmentScreenChangeBackground)
                        ViewModelScreenSettings.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenChangeCards ->
                            navigateToActionId(R.id.action_fragmentScreenSettings_to_fragmentScreenChangeCards)
                    }
                }
            }
        }
    }
}