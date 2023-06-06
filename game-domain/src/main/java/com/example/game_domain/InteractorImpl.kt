package com.example.game_domain

import com.example.common.LevelInfo
import com.example.common.NumberOfStars
import com.example.game_domain.usecases.LoadLevelsInfoFromDBUseCase
import com.example.game_domain.usecases.UpdateNumberOfStarsInDBUseCase
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val loadLevelsInfoFromDBUseCase: LoadLevelsInfoFromDBUseCase,
    private val updateNumberOfStarsInDBUseCase: UpdateNumberOfStarsInDBUseCase
) : Interactor {
    override suspend fun loadLevelsInfoFromDB(): List<LevelInfo> =
        loadLevelsInfoFromDBUseCase.execute()

    override suspend fun updateNumberOfStars(id: Long, newNumberOfStars: NumberOfStars): Boolean =
        updateNumberOfStarsInDBUseCase.execute(id, newNumberOfStars)
}