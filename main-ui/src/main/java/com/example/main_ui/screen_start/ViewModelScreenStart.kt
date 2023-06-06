package com.example.main_ui.screen_start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import com.example.common.ScreenSettings
import com.example.main_domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelScreenStart @Inject constructor(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenStart.Model>(Model()) {

    fun buttonPlayPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels
            )
        )
    }

    fun buttonHowToPlayPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenHowToPlay
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
                ScreenHowToPlay
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
            require(modelClass == ViewModelScreenStart::class.java)
            return ViewModelScreenStart(
                interactor
            ) as T
        }
    }
}