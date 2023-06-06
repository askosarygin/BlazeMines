package com.example.main_ui.screen_start

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
import com.example.main_ui.databinding.FragmentScreenStartBinding
import com.example.main_ui.di.MainComponentViewModel
import javax.inject.Inject

class FragmentScreenStart : BlazeMinesFragment(R.layout.fragment_screen_start) {
    private lateinit var binding: FragmentScreenStartBinding

    @Inject
    lateinit var factory: ViewModelScreenStart.Factory

    @Inject
    lateinit var navHostsInfo: NavHostsInfo

    private val viewModel by viewModels<ViewModelScreenStart> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenStartBinding.inflate(
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
        binding.btnPlay.setOnClickListener {
            viewModel.buttonPlayPressed()
        }

        binding.btnHowToPlay.setOnClickListener {
            viewModel.buttonHowToPlayPressed()
        }
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenStart.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels ->
                            navigateToModuleScreen(
                                ModuleNames.Game,
                                ScreenNames.ScreenLevels,
                                navHostsInfo.globalNavHostId
                            )
                        ViewModelScreenStart.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenHowToPlay ->
                            navigateToActionId(R.id.action_fragmentScreenStart_to_fragmentScreenHowToPlay)
                    }
                }
            }
        }
    }
}