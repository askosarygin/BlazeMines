package com.example.settings_ui.screen_change_background

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import com.example.settings_domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelScreenChangeBackground @Inject constructor(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenChangeBackground.Model>(Model()) {

    fun initScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedBackgroundId = interactor.getBackgroundIdFromAppData()

            updateSelectedBackground(selectedBackgroundId)
        }
    }

    fun buttonBackPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings
            )
        )
    }

    fun changeSelectedBackground(selectedBackground: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveBackgroundIdToAppData(selectedBackground)
        }

        updateSelectedBackground(selectedBackground)
    }

    data class Model(
        val selectedBackgroundId: Int = 1,
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

    private fun updateSelectedBackground(selectedBackground: Int) {
        update {
            it.copy(
                selectedBackgroundId = selectedBackground
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
            require(modelClass == ViewModelScreenChangeBackground::class.java)
            return ViewModelScreenChangeBackground(
                interactor
            ) as T
        }
    }
}