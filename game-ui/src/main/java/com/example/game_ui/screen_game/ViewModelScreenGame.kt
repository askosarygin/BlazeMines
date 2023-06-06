package com.example.game_ui.screen_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.*
import com.example.game_domain.Interactor
import com.example.game_ui.common.LevelsInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelScreenGame(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenGame.Model>(Model()) {

    fun initScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val screenSettings = interactor.getScreenSettings()

            updateScreenSettings(screenSettings)
        }
    }

    fun saveStarsInLevelsDB(
        id: Long,
        newNumberOfStars: NumberOfStars
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.updateNumberOfStarsInDB(
                id,
                newNumberOfStars
            )
        }
    }

    fun initLevelInfo(levelsInfo: LevelsInfo) {
        updateCurrentLevelInfo(levelsInfo.levelsInfo[levelsInfo.currentLevelIndex])

        updateLevelsInfo(levelsInfo)

        updateLeftToFindFires(levelsInfo.levelsInfo[levelsInfo.currentLevelIndex].numberOfFires)
    }

    fun buttonBackPressed() {
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

    fun cellClickedFire() {
        updateLeftToFindFires(model.value.leftToFindFires - 1)
    }

    fun cellClickedBomb() {
        updateLifeHeartsCount(model.value.lifeHeartsCount - 1)
    }

    data class Model(
        val screenSettings: ScreenSettings = ScreenSettings(),
        val leftToFindFires: Int = -1,
        val lifeHeartsCount: Int = 3,
        val currentLevelInfo: LevelInfo? = null,
        val levelsInfo: LevelsInfo = LevelsInfo(),
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

    private fun updateLeftToFindFires(leftToFindFires: Int) {
        update {
            it.copy(
                leftToFindFires = leftToFindFires
            )
        }
    }

    private fun updateScreenSettings(screenSettings: ScreenSettings) {
        update {
            it.copy(
                screenSettings = screenSettings
            )
        }
    }

    private fun updateLifeHeartsCount(lifeHeartsCount: Int) {
        update {
            it.copy(
                lifeHeartsCount = lifeHeartsCount
            )
        }
    }

    private fun updateLevelsInfo(levelsInfo: LevelsInfo) {
        update {
            it.copy(
                levelsInfo = levelsInfo
            )
        }
    }

    private fun updateCurrentLevelInfo(currentLevelInfo: LevelInfo) {
        update {
            it.copy(
                currentLevelInfo = currentLevelInfo
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
            require(modelClass == ViewModelScreenGame::class.java)
            return ViewModelScreenGame(
                interactor
            ) as T
        }
    }
}