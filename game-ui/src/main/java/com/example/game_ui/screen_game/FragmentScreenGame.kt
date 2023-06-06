package com.example.game_ui.screen_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.*
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

    override fun onResume() {
        super.onResume()

        val levelInfo =
            getBundleNavigation(resources.getString(com.example.common.R.string.blaze_mines_bundle_key_level_info)) as LevelInfo

        viewModel.initLevelInfo(levelInfo)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initLevel(levelInfo: LevelInfo) {
        val cellsAll = listOf(
            binding.ivCell1,
            binding.ivCell2,
            binding.ivCell3,
            binding.ivCell4,
            binding.ivCell5,
            binding.ivCell6,
            binding.ivCell7,
            binding.ivCell8,
            binding.ivCell9,
            binding.ivCell10,
            binding.ivCell11,
            binding.ivCell12,
            binding.ivCell13,
            binding.ivCell14,
            binding.ivCell15
        )

        val cellsForGame = cellsAll.slice(0 until levelInfo.numberOfCells)

        Log.i("MY_TAG", "numberOfCells = ${levelInfo.numberOfCells}")
        Log.i("MY_TAG", "cellsForGame = ${cellsForGame.size}")

        cellsForGame.forEach {
            it.setImageDrawable(resources.getDrawable(R.drawable.icon_cell_bomb_1))
        }

        Log.i("MY_TAG", "cellsWithFire = ${levelInfo.numberOfFires}")

        cellsForGame.shuffled().slice(0 until levelInfo.numberOfFires).forEach {
            it.setImageDrawable(resources.getDrawable(R.drawable.icon_cell_fire_1))
        }

        cellsForGame.forEach {
            it.visibility = View.VISIBLE
        }
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

            if (oldModel?.levelInfo != newModel.levelInfo) {
                newModel.levelInfo?.let {
                    initLevel(it)
                }
            }
        }
    }
}