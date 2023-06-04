package com.example.settings_ui.screen_change_cards

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
                        ViewModelScreenChangeCards.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings ->
                            navigateToActionId(R.id.action_fragmentScreenChangeCards_to_fragmentScreenSettings)
                    }
                }
            }
        }
    }
}