package com.example.game_ui.screen_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import com.example.common.LevelInfo
import com.example.common.NumberOfStars
import com.example.game_domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelScreenGame(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenGame.Model>(Model()) {

    fun saveStarsInLevelsDB(
        id: Long,
        newNumberOfStars: NumberOfStars
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.updateNumberOfStars(
                id,
                newNumberOfStars
            )
        }
    }

    fun initLevelInfo(levelInfo: LevelInfo) {
        updateLevelInfo(levelInfo)
        updateLeftToFindFires(levelInfo.numberOfFires)
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
        val leftToFindFires: Int = -1,
        val lifeHeartsCount: Int = 3,
        val currentLevelInfo: LevelInfo? = null,
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

    private fun updateLifeHeartsCount(lifeHeartsCount: Int) {
        update {
            it.copy(
                lifeHeartsCount = lifeHeartsCount
            )
        }
    }

    private fun updateLevelInfo(levelInfo: LevelInfo) {
        update {
            it.copy(
                currentLevelInfo = levelInfo
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