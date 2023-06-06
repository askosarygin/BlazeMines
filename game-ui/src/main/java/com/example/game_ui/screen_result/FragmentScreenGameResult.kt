package com.example.game_ui.screen_result

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.common.NavHostsInfo
import com.example.game_ui.R
import com.example.game_ui.common.LevelResult
import com.example.game_ui.databinding.FragmentScreenGameResultBinding
import com.example.game_ui.di.GameComponentViewModel
import javax.inject.Inject

class FragmentScreenGameResult : BlazeMinesFragment(R.layout.fragment_screen_game_result) {
    private lateinit var binding: FragmentScreenGameResultBinding

    @Inject
    lateinit var factory: ViewModelScreenGameResult.Factory

    @Inject
    lateinit var navHostsInfo: NavHostsInfo

    private val viewModel by viewModels<ViewModelScreenGameResult> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenGameResultBinding.inflate(
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

        val levelResult =
            getBundleNavigation(resources.getString(com.example.common.R.string.blaze_mines_bundle_key_game_result)) as LevelResult

        viewModel.setLevelResult(levelResult)
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.buttonBackPressed()
        }

        binding.btnReplay.setOnClickListener {
            navigateToActionId(
                R.id.action_fragmentScreenGameResult_to_fragmentScreenGame,
                viewModel.model.value.levelResult.currentLevelInfo,
                resources.getString(com.example.common.R.string.blaze_mines_bundle_key_level_info)
            )
        }

        binding.btnNext.setOnClickListener {
            navigateToActionId(
                R.id.action_fragmentScreenLevels_to_fragmentScreenGame,
                viewModel.model.value.levelResult.nextLevelInfo,
                resources.getString(com.example.common.R.string.blaze_mines_bundle_key_level_info)
            )
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenGameResult.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels ->
                            navigateToActionId(R.id.action_fragmentScreenGameResult_to_fragmentScreenLevels)
                    }
                }
            }

            if (oldModel?.levelResult != newModel.levelResult) {
                when (newModel.levelResult.starsCount) {
                    0 -> binding.ivResultStars.background =
                        resources.getDrawable(R.drawable.icon_stars_0)
                    1 -> binding.ivResultStars.background =
                        resources.getDrawable(R.drawable.icon_stars_1)
                    2 -> binding.ivResultStars.background =
                        resources.getDrawable(R.drawable.icon_stars_2)
                    3 -> binding.ivResultStars.background =
                        resources.getDrawable(R.drawable.icon_stars_3)
                }

                binding.tvLevelNumber.text = newModel.levelResult.levelName

                binding.tvFoundFires.text = buildString {
                    append(newModel.levelResult.foundFires)
                    append("/")
                    append(newModel.levelResult.needFires)
                }
            }
        }
    }
}