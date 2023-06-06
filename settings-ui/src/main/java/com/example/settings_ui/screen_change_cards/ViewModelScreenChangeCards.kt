package com.example.settings_ui.screen_change_cards

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

class ViewModelScreenChangeCards @Inject constructor(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenChangeCards.Model>(Model()) {

    fun initScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val screenSettings = interactor.getScreenSettings()

            updateScreenSettings(screenSettings)

            updateSelectedFireIconId(screenSettings.fireIconId)
            updateSelectedBombIconId(screenSettings.bombIconId)
        }
    }

    fun buttonBackPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings
            )
        )
    }

    fun changeSelectedFireIconId(selectedFireIconId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveFireIconIdToAppData(selectedFireIconId)
        }

        updateSelectedFireIconId(selectedFireIconId)
    }

    fun changeSelectedBombIconId(selectedBombIconId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveBombIconIdToAppData(selectedBombIconId)
        }

        updateSelectedBombIconId(selectedBombIconId)
    }

    data class Model(
        val screenSettings: ScreenSettings = ScreenSettings(),
        val selectedFireIconId: Int = 1,
        val selectedBombIconId: Int = 1,
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

    private fun updateScreenSettings(screenSettings: ScreenSettings) {
        update {
            it.copy(
                screenSettings = screenSettings
            )
        }
    }

    private fun updateSelectedFireIconId(selectedFireIconId: Int) {
        update {
            it.copy(
                selectedFireIconId = selectedFireIconId
            )
        }
    }

    private fun updateSelectedBombIconId(selectedBombIconId: Int) {
        update {
            it.copy(
                selectedBombIconId = selectedBombIconId
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
            require(modelClass == ViewModelScreenChangeCards::class.java)
            return ViewModelScreenChangeCards(
                interactor
            ) as T
        }
    }
}