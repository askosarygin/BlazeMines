package com.example.settings_ui.screen_change_cards

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.settings_ui.R
import com.example.settings_ui.databinding.FragmentScreenChangeCardsBinding
import com.example.settings_ui.di.SettingsComponentViewModel
import javax.inject.Inject

class FragmentScreenChangeCards : BlazeMinesFragment(R.layout.fragment_screen_change_cards) {
    private lateinit var binding: FragmentScreenChangeCardsBinding

    @Inject
    lateinit var factory: ViewModelScreenChangeCards.Factory

    private val viewModel by viewModels<ViewModelScreenChangeCards> {
        factory
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenChangeCardsBinding.inflate(
            inflater,
            container,
            false
        )

        SettingsComponentViewModel.getComponent().inject(this)

        initCollect()

        initListeners()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.initScreen()
    }


    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.buttonBackPressed()
        }
        binding.ivBomb1.setOnClickListener {
            viewModel.changeSelectedBombIconId(1)
        }
        binding.ivBomb2.setOnClickListener {
            viewModel.changeSelectedBombIconId(2)
        }
        binding.ivFire1.setOnClickListener {
            viewModel.changeSelectedFireIconId(1)
        }
        binding.ivFire2.setOnClickListener {
            viewModel.changeSelectedFireIconId(2)
        }
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenChangeCards.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings ->
                            navigateToActionId(R.id.action_fragmentScreenChangeCards_to_fragmentScreenSettings)
                    }
                }
            }

            if (oldModel?.selectedBombIconId != newModel.selectedBombIconId) {
                when (newModel.selectedBombIconId) {
                    1 -> {
                        binding.ivCheckboxBomb1.visibility = View.VISIBLE
                        binding.ivCheckboxBomb2.visibility = View.INVISIBLE
                    }
                    2 -> {
                        binding.ivCheckboxBomb1.visibility = View.INVISIBLE
                        binding.ivCheckboxBomb2.visibility = View.VISIBLE
                    }
                }
            }

            if (oldModel?.selectedFireIconId != newModel.selectedFireIconId) {
                when (newModel.selectedFireIconId) {
                    1 -> {
                        binding.ivCheckboxFire1.visibility = View.VISIBLE
                        binding.ivCheckboxFire2.visibility = View.INVISIBLE
                    }
                    2 -> {
                        binding.ivCheckboxFire1.visibility = View.INVISIBLE
                        binding.ivCheckboxFire2.visibility = View.VISIBLE
                    }
                }
            }

            if (oldModel?.screenSettings != newModel.screenSettings) {
                changeScreenSettings(
                    newModel.screenSettings,
                    binding.root
                )
            }
        }
    }
}