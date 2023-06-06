package com.example.game_ui.screen_game

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
import com.example.game_ui.databinding.FragmentScreenGameBinding
import com.example.game_ui.di.GameComponentViewModel
import javax.inject.Inject

class FragmentScreenGame : BlazeMinesFragment(R.layout.fragment_screen_game) {
    private lateinit var binding: FragmentScreenGameBinding

    @Inject
    lateinit var factory: ViewModelScreenGame.Factory

    @Inject
    lateinit var navHostsInfo: NavHostsInfo

    private val viewModel by viewModels<ViewModelScreenGame> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenGameBinding.inflate(
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

        binding.btnHowToPlay.setOnClickListener {
            viewModel.buttonHowToPlayPressed()
        }
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenGame.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels ->
                            navigateToActionId(R.id.action_fragmentScreenGame_to_fragmentScreenLevels)
                        ViewModelScreenGame.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenHowToPlay ->
                            navigateToModuleScreen(
                                ModuleNames.Main,
                                ScreenNames.ScreenHowToPlay,
                                navHostsInfo.globalNavHostId
                            )
                    }
                }
            }
        }
    }
}