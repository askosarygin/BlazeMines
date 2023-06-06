package com.example.game_ui.screen_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.BlazeMinesViewModel
import com.example.common.BlazeMinesViewModelSingleLifeEvent
import com.example.common.ScreenSettings
import com.example.game_domain.Interactor
import com.example.game_ui.common.LevelResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelScreenGameResult @Inject constructor(
    private val interactor: Interactor
) : BlazeMinesViewModel<ViewModelScreenGameResult.Model>(Model()) {

    fun initScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val screenSettings = interactor.getScreenSettings()

            updateScreenSettings(screenSettings)
        }
    }

    fun activateCell(
        id: Long,
        activated: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.updateCellActivationInDB(
                id,
                activated
            )
        }
    }

    fun buttonBackPressed() {
        updateNavigationEvent(
            Model.NavigationSingleLifeEvent(
                Model.NavigationSingleLifeEvent.NavigationDestination.ScreenLevels
            )
        )
    }

    fun setLevelResult(levelResult: LevelResult) {
        updateLevelResult(levelResult)
    }

    data class Model(
        val screenSettings: ScreenSettings = ScreenSettings(),
        val levelResult: LevelResult = LevelResult(),
        val navigationEvent: NavigationSingleLifeEvent? = null
    ) {
        class NavigationSingleLifeEvent(
            navigateTo: NavigationDestination
        ) : BlazeMinesViewModelSingleLifeEvent<NavigationSingleLifeEvent.NavigationDestination>(
            navigateTo
        ) {
            enum class NavigationDestination {
                ScreenLevels
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

    private fun updateLevelResult(levelResult: LevelResult) {
        update {
            it.copy(
                levelResult = levelResult
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
            require(modelClass == ViewModelScreenGameResult::class.java)
            return ViewModelScreenGameResult(
                interactor
            ) as T
        }
    }
}