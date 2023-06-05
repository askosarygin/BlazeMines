package com.example.main_ui.screen_levels

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
import com.example.main_ui.R
import com.example.main_ui.databinding.FragmentScreenLevelsBinding
import com.example.main_ui.di.MainComponentViewModel
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

        MainComponentViewModel.getComponent().inject(this)

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
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenLevels.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart ->
                            navigateToActionId(R.id.action_fragmentScreenLevels_to_fragmentScreenStart)
                        ViewModelScreenLevels.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings ->
                            navigateToModuleScreen(
                                ModuleNames.Settings,
                                ScreenNames.ScreenSettings,
                                navHostsInfo.globalNavHostId
                            )
                    }
                }
            }
        }
    }
}