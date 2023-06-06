package com.example.game_domain

import com.example.common.LevelInfo
import com.example.common.NumberOfStars
import com.example.common.ScreenSettings
import com.example.game_domain.usecases.GetScreenSettingsUseCase
import com.example.game_domain.usecases.LoadLevelsInfoFromDBUseCase
import com.example.game_domain.usecases.UpdateCellActivationInDBUseCase
import com.example.game_domain.usecases.UpdateNumberOfStarsInDBUseCase
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val loadLevelsInfoFromDBUseCase: LoadLevelsInfoFromDBUseCase,
    private val updateNumberOfStarsInDBUseCase: UpdateNumberOfStarsInDBUseCase,
    private val updateCellActivationInDBUseCase: UpdateCellActivationInDBUseCase,
    private val getScreenSettingsUseCase: GetScreenSettingsUseCase
) : Interactor {
    override suspend fun loadLevelsInfoFromDB(): List<LevelInfo> =
        loadLevelsInfoFromDBUseCase.execute()

    override suspend fun updateNumberOfStarsInDB(id: Long, newNumberOfStars: NumberOfStars): Boolean =
        updateNumberOfStarsInDBUseCase.execute(id, newNumberOfStars)

    override suspend fun updateCellActivationInDB(id: Long, activated: Boolean): Boolean =
        updateCellActivationInDBUseCase.execute(id, activated)

    override suspend fun getScreenSettings(): ScreenSettings =
        getScreenSettingsUseCase.execute()
}