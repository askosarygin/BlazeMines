package com.example.game_ui.screen_levels

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.*
import com.example.game_ui.R
import com.example.game_ui.common.LevelsInfo
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initLevels(levelsInfo: List<LevelInfo>) {
        val cellStars = listOf(
            binding.ivCell1Stars,
            binding.ivCell2Stars,
            binding.ivCell3Stars,
            binding.ivCell4Stars,
            binding.ivCell5Stars,
            binding.ivCell6Stars,
            binding.ivCell7Stars,
            binding.ivCell8Stars,
            binding.ivCell9Stars,
            binding.ivCell10Stars,
            binding.ivCell11Stars,
            binding.ivCell12Stars,
            binding.ivCell13Stars,
            binding.ivCell14Stars,
            binding.ivCell15Stars
        )

        val cells = listOf(
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

        val cellsActivatedBackground = listOf(
            resources.getDrawable(R.drawable.icon_level_activated_1),
            resources.getDrawable(R.drawable.icon_level_activated_2),
            resources.getDrawable(R.drawable.icon_level_activated_3),
            resources.getDrawable(R.drawable.icon_level_activated_4),
            resources.getDrawable(R.drawable.icon_level_activated_5),
            resources.getDrawable(R.drawable.icon_level_activated_6),
            resources.getDrawable(R.drawable.icon_level_activated_7),
            resources.getDrawable(R.drawable.icon_level_activated_8),
            resources.getDrawable(R.drawable.icon_level_activated_9),
            resources.getDrawable(R.drawable.icon_level_activated_10),
            resources.getDrawable(R.drawable.icon_level_activated_11),
            resources.getDrawable(R.drawable.icon_level_activated_12),
            resources.getDrawable(R.drawable.icon_level_activated_13),
            resources.getDrawable(R.drawable.icon_level_activated_14),
            resources.getDrawable(R.drawable.icon_level_activated_15),
        )

        val cellsUnactivatedBackground = listOf(
            resources.getDrawable(R.drawable.icon_level_unactivated_1),
            resources.getDrawable(R.drawable.icon_level_unactivated_2),
            resources.getDrawable(R.drawable.icon_level_unactivated_3),
            resources.getDrawable(R.drawable.icon_level_unactivated_4),
            resources.getDrawable(R.drawable.icon_level_unactivated_5),
            resources.getDrawable(R.drawable.icon_level_unactivated_6),
            resources.getDrawable(R.drawable.icon_level_unactivated_7),
            resources.getDrawable(R.drawable.icon_level_unactivated_8),
            resources.getDrawable(R.drawable.icon_level_unactivated_9),
            resources.getDrawable(R.drawable.icon_level_unactivated_10),
            resources.getDrawable(R.drawable.icon_level_unactivated_11),
            resources.getDrawable(R.drawable.icon_level_unactivated_12),
            resources.getDrawable(R.drawable.icon_level_unactivated_13),
            resources.getDrawable(R.drawable.icon_level_unactivated_14),
            resources.getDrawable(R.drawable.icon_level_unactivated_15),
        )

        val starsDrawable = listOf(
            resources.getDrawable(R.drawable.icon_stars_0),
            resources.getDrawable(R.drawable.icon_stars_1),
            resources.getDrawable(R.drawable.icon_stars_2),
            resources.getDrawable(R.drawable.icon_stars_3)
        )

        levelsInfo.forEachIndexed { index, levelInfo ->
            val activeCell = if (levelInfo.activated) {
                cellsActivatedBackground[index]
            } else {
                cellsUnactivatedBackground[index]
            }

            cells[index].setBackgroundDrawable(activeCell)

            val stars = when (levelInfo.numberOfStars) {
                0 -> starsDrawable[0]
                1 -> starsDrawable[1]
                2 -> starsDrawable[2]
                3 -> starsDrawable[3]
                else -> {
                    starsDrawable[0]
                }
            }

            cellStars[index].setImageDrawable(stars)

            cells[index].setOnClickListener {
                val nextLevelIndex = if (index == 14) {
                    0
                } else {
                    index + 1
                }

                viewModel.levelSelected(
                    LevelsInfo(
                        index,
                        nextLevelIndex,
                        levelsInfo
                    )
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadLevelsInfo()
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
                            navigateToActionId(
                                R.id.action_fragmentScreenLevels_to_fragmentScreenGame,
                                newModel.currentAndNextLevels,
                                resources.getString(com.example.common.R.string.blaze_mines_bundle_key_levels_info)
                            )
                    }
                }
            }
            if (oldModel?.levelsInfo != newModel.levelsInfo) {
                if (newModel.levelsInfo.isNotEmpty()) {
                    initLevels(newModel.levelsInfo)
                }
            }
        }
    }
}