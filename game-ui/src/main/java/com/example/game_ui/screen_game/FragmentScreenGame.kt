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
import com.example.game_ui.common.LevelResult
import com.example.game_ui.databinding.FragmentScreenGameBinding
import com.example.game_ui.di.GameComponentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
        lifecycleScope.launch {
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

            val cells = cellsAll.slice(0 until levelInfo.numberOfCells)

            val cellsForGame = cells.map { cellView ->
                CellInfo(
                    cellView
                )
            }

            cellsForGame.forEach {
                it.cellView.setImageDrawable(resources.getDrawable(R.drawable.icon_cell_bomb_1))
            }

            cellsForGame.shuffled().slice(0 until levelInfo.numberOfFires).forEach {
                it.cellView.setImageDrawable(resources.getDrawable(R.drawable.icon_cell_fire_1))
                it.isFire = true
            }

            cellsForGame.forEach {
                it.cellView.visibility = View.VISIBLE
            }

            binding.tvFireCount.text = buildString {
                append("0/")
                append(levelInfo.numberOfFires)
            }

            delay(2000L)//todo

            cellsForGame.forEach {
                it.cellView.setImageDrawable(resources.getDrawable(R.drawable.icon_cell_empty))
            }

            cellsForGame.forEach { cellInfo ->
                cellInfo.cellView.setOnClickListener {
                    cellClicked(cellInfo)
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun cellClicked(cellInfo: CellInfo) {
        if (!cellInfo.isSelected) {
            cellInfo.isSelected = true

            if (cellInfo.isFire) {
                cellInfo.cellView.setImageDrawable(resources.getDrawable(R.drawable.icon_cell_fire_1))
                viewModel.cellClickedFire()
            } else {
                cellInfo.cellView.setImageDrawable(resources.getDrawable(R.drawable.icon_cell_bomb_1))
                viewModel.cellClickedBomb()
            }
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

    private fun directionToResultGame(levelResult: LevelResult) {
        navigateToActionId(
            R.id.action_fragmentScreenGame_to_fragmentScreenGameResult,
            levelResult,
            resources.getString(com.example.common.R.string.blaze_mines_bundle_key_game_result)
        )
    }

    private fun getLevelResult(): LevelResult {
        return LevelResult(
            buildString {
                append("Level ")
                append(viewModel.model.value.levelInfo!!.number)
            },
            viewModel.model.value.levelInfo!!.numberOfFires - viewModel.model.value.leftToFindFires,
            viewModel.model.value.levelInfo!!.numberOfFires,
            viewModel.model.value.lifeHeartsCount,
            viewModel.model.value.levelInfo!!
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
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

            if (oldModel?.lifeHeartsCount != newModel.lifeHeartsCount) {
                when (newModel.lifeHeartsCount) {
                    0 -> {
                        binding.ivLifeHearts.background =
                            resources.getDrawable(R.drawable.icon_life_hearts_0)
                        //todo направление на экран результатов
                        Log.i("MY_TAG", "Все жизни закончились")
                        directionToResultGame(getLevelResult())
                    }
                    1 -> binding.ivLifeHearts.background =
                        resources.getDrawable(R.drawable.icon_life_hearts_1)
                    2 -> binding.ivLifeHearts.background =
                        resources.getDrawable(R.drawable.icon_life_hearts_2)
                    3 -> binding.ivLifeHearts.background =
                        resources.getDrawable(R.drawable.icon_life_hearts_3)
                }
            }

            if (oldModel?.leftToFindFires != newModel.leftToFindFires) {
                newModel.levelInfo?.let {
                    binding.tvFireCount.text = buildString {
                        append(newModel.levelInfo.numberOfFires - newModel.leftToFindFires)
                        append("/")
                        append(newModel.levelInfo.numberOfFires)
                    }
                }
                if (newModel.leftToFindFires == 0) {
                    directionToResultGame(getLevelResult())
                    Log.i("MY_TAG", "Все огоньки найдены")
                }
            }
        }
    }
}