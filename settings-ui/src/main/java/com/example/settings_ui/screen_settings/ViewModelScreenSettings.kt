package com.example.settings_ui.screen_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import com.example.common.ScreenSettings
import com.example.settings_domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelScreenSettings @Inject constructor(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenSettings.Model>(Model()) {

    fun buttonBackPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels
            )
        )
    }

    fun buttonBackgroundPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenChangeBackground
            )
        )
    }

    fun buttonCardsPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenChangeCards
            )
        )
    }

    fun initScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val screenSettings = interactor.getScreenSettings()

            updateScreenSettings(screenSettings)
        }
    }

    data class Model(
        val screenSettings: ScreenSettings = ScreenSettings(),
        val navigationEvent: NavigationSingleLifeEvent? = null
    ) {
        class NavigationSingleLifeEvent(
            navigateTo: NavigationDestination
        ) : BlazeMinesViewModelSingleLifeEvent<NavigationSingleLifeEvent.NavigationDestination>(
            navigateTo
        ) {
            enum class NavigationDestination {
                ScreenLevels,
                ScreenChangeBackground,
                ScreenChangeCards
            }
        }
    }

    private fun updateScreenSettings(screenSettings: ScreenSettings) {
        update {
            it.copy(
                screenSettings = screenSettings
            )
        }
    }

    private fun updateNavigationEvent(navigationEvent: Model.NavigationSingleLifeEvent) {
        update {
            it.copy(
                navigationEvent = navigationEvent
            )
        }
    }

    class Factory @Inject constructor(
        private val interactor: Interactor
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ViewModelScreenSettings::class.java)
            return ViewModelScreenSettings(
                interactor
            ) as T
        }
    }
}