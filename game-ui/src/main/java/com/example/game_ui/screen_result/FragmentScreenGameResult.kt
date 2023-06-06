package com.example.game_ui.screen_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.common.NavHostsInfo
import com.example.game_ui.R
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
                        ViewModelScreenGameResult.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels ->
                            navigateToActionId(R.id.action_fragmentScreenGameResult_to_fragmentScreenLevels)
                    }
                }
            }
        }
    }
}