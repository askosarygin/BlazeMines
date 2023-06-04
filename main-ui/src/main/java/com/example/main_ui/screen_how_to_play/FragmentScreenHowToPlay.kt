package com.example.main_ui.screen_how_to_play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.main_ui.R
import com.example.main_ui.databinding.FragmentScreenHowToPlayBinding
import com.example.main_ui.di.MainComponentViewModel
import javax.inject.Inject

class FragmentScreenHowToPlay : BlazeMinesFragment(R.layout.fragment_screen_how_to_play) {
    private lateinit var binding: FragmentScreenHowToPlayBinding

    @Inject
    lateinit var factory: ViewModelScreenHowToPlay.Factory

    private val viewModel by viewModels<ViewModelScreenHowToPlay> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenHowToPlayBinding.inflate(
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
    }

    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenHowToPlay.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart ->
                            navigateToActionId(
                                R.id.action_fragmentScreenHowToPlay_to_fragmentScreenStart
                            )
                    }
                }
            }
        }
    }
}