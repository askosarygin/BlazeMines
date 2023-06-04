package com.example.settings_ui.screen_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import javax.inject.Inject

class ViewModelScreenSettings :
    BlazeMinesViewModel<ViewModelScreenSettings.Model>(Model()) {

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

    data class Model(
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

    private fun updateNavigationEvent(navigationEvent: Model.NavigationSingleLifeEvent) {
        update {
            it.copy(
                navigationEvent = navigationEvent
            )
        }
    }

    class Factory @Inject constructor(
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ViewModelScreenSettings::class.java)
            return ViewModelScreenSettings(

            ) as T
        }
    }
}