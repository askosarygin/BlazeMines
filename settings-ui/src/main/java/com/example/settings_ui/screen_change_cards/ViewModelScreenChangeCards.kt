package com.example.settings_ui.screen_change_cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import javax.inject.Inject

class ViewModelScreenChangeCards :
    BlazeMinesViewModel<ViewModelScreenChangeCards.Model>(Model()) {

    fun buttonBackPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings
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
                ScreenSettings
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
            require(modelClass == ViewModelScreenChangeCards::class.java)
            return ViewModelScreenChangeCards(

            ) as T
        }
    }
}