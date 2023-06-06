package com.example.game_domain

import com.example.common.LevelInfo
import com.example.game_domain.usecases.LoadLevelsInfoFromDBUseCase
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val loadLevelsInfoFromDBUseCase: LoadLevelsInfoFromDBUseCase
) : Interactor {
    override suspend fun loadLevelsInfoFromDB(): List<LevelInfo> = loadLevelsInfoFromDBUseCase.execute()
}