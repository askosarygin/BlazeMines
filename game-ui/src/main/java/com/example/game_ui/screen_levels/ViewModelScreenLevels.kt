package com.example.game_ui.screen_levels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import com.example.common.LevelInfo
import com.example.game_domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelScreenLevels(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenLevels.Model>(Model()) {

    fun levelSelected(selectedLevelInfo: LevelInfo) {
        updateSelectedLevelInfo(selectedLevelInfo)

        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenGame
            )
        )
    }

    fun loadLevelsInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val levelsInfo = interactor.loadLevelsInfoFromDB()

            updateLevelsInfo(levelsInfo)
        }
    }

    fun buttonBackPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenStart
            )
        )
    }

    fun buttonSettingsPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenSettings
            )
        )
    }

    data class Model(
        val selectedLevelInfo: LevelInfo = LevelInfo(),
        val levelsInfo: List<LevelInfo> = listOf(),
        val navigationEvent: NavigationSingleLifeEvent? = null
    ) {
        class NavigationSingleLifeEvent(
            navigateTo: NavigationDestination
        ) : BlazeMinesViewModelSingleLifeEvent<NavigationSingleLifeEvent.NavigationDestination>(
            navigateTo
        ) {
            enum class NavigationDestination {
                ScreenStart,
                ScreenSettings,
                ScreenGame
            }
        }
    }

    private fun updateSelectedLevelInfo(selectedLevelInfo: LevelInfo) {
        update {
            it.copy(
                selectedLevelInfo = selectedLevelInfo
            )
        }
    }

    private fun updateLevelsInfo(levelsInfo: List<LevelInfo>) {
        update {
            it.copy(
                levelsInfo = levelsInfo
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
            require(modelClass == ViewModelScreenLevels::class.java)
            return ViewModelScreenLevels(
                interactor
            ) as T
        }
    }
}