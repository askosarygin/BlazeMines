package com.example.game_ui.screen_levels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.common.ModuleNames
import com.example.common.NavHostsInfo
import com.example.common.ScreenNames
import com.example.game_ui.R
import com.example.game_ui.databinding.FragmentScreenLevelsBinding
import com.example.game_ui.di.GameComponentViewModel
import javax.inject.Inject

class FragmentScreenLevels : BlazeMinesFragment(R.layout.fragment_screen_levels) {
    private lateinit var binding: FragmentScreenLevelsBinding

    @Inject
    lateinit var factory: ViewModelScreenLevels.Factory

    @Inject
    lateinit var navHostsInfo: NavHostsInfo

    private val viewModel by viewModels<ViewModelScreenLevels> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenLevelsBinding.inflate(
            inflater,
            container,
            false
        )

        GameComponentViewModel.getComponent().inject(this)

        initCollect()

        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.buttonBackPressed()
        }

        binding.btnSettings.setOnClickListener {
            viewModel.buttonSettingsPressed()
        }
        binding.tvLevels.setOnClickListener{
            viewModel.buttonLevelsPressed()
        }
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenLevels.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart ->
                            navigateToModuleScreen(
                                ModuleNames.Main,
                                ScreenNames.ScreenStart,
                                navHostsInfo.globalNavHostId
                            )
                        ViewModelScreenLevels.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings ->
                            navigateToModuleScreen(
                                ModuleNames.Settings,
                                ScreenNames.ScreenSettings,
                                navHostsInfo.globalNavHostId
                            )
                        ViewModelScreenLevels.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGame ->
                            navigateToActionId(R.id.action_fragmentScreenLevels_to_fragmentScreenGame)
                    }
                }
            }
        }
    }
}