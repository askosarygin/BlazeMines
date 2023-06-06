package com.example.settings_ui.screen_change_background

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.common.BlazeMinesFragment
import com.example.settings_ui.R
import com.example.settings_ui.databinding.FragmentScreenChangeBackgroundBinding
import com.example.settings_ui.di.SettingsComponentViewModel
import javax.inject.Inject

class FragmentScreenChangeBackground :
    BlazeMinesFragment(R.layout.fragment_screen_change_background) {
    private lateinit var binding: FragmentScreenChangeBackgroundBinding

    @Inject
    lateinit var factory: ViewModelScreenChangeBackground.Factory

    private val viewModel by viewModels<ViewModelScreenChangeBackground> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreenChangeBackgroundBinding.inflate(
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

        binding.ivBackground1.setOnClickListener {
            viewModel.changeSelectedBackground(1)
        }

        binding.ivBackground2.setOnClickListener {
            viewModel.changeSelectedBackground(2)
        }

        binding.ivBackground3.setOnClickListener {
            viewModel.changeSelectedBackground(3)
        }

        binding.ivBackground4.setOnClickListener {
            viewModel.changeSelectedBackground(4)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initCollect() {
        viewModel.model.collectWithOld(lifecycleScope) { oldModel, newModel ->
            if (oldModel?.navigationEvent != newModel.navigationEvent) {
                newModel.navigationEvent?.use { navigationDestination ->
                    when (navigationDestination) {
                        ViewModelScreenChangeBackground.Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings ->
                            navigateToActionId(R.id.action_fragmentScreenChangeBackground_to_fragmentScreenSettings)

                    }
                }
            }

            if (oldModel?.selectedBackgroundId != newModel.selectedBackgroundId) {

                when (newModel.selectedBackgroundId) {
                    1 -> {
                        binding.ivCheckboxBackground1.visibility = View.VISIBLE
                        binding.ivCheckboxBackground2.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground3.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground4.visibility = View.INVISIBLE

                        binding.root.setBackgroundColor(resources.getColor(com.example.common.R.color.blaze_mines_black_2))
                    }
                    2 -> {
                        binding.ivCheckboxBackground1.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground2.visibility = View.VISIBLE
                        binding.ivCheckboxBackground3.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground4.visibility = View.INVISIBLE

                        binding.root.background = resources.getDrawable(com.example.common.R.drawable.background_2)
                    }
                    3 -> {
                        binding.ivCheckboxBackground1.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground2.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground3.visibility = View.VISIBLE
                        binding.ivCheckboxBackground4.visibility = View.INVISIBLE

                        binding.root.background = resources.getDrawable(com.example.common.R.drawable.background_3)
                    }
                    4 -> {
                        binding.ivCheckboxBackground1.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground2.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground3.visibility = View.INVISIBLE
                        binding.ivCheckboxBackground4.visibility = View.VISIBLE

                        binding.root.background = resources.getDrawable(com.example.common.R.drawable.background_4)
                    }
                }
            }
        }
    }
}