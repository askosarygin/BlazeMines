package com.example.game_domain.usecases

import com.example.common.LevelInfo
import com.example.game_domain.Repository
import javax.inject.Inject

class LoadLevelsInfoFromDBUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): List<LevelInfo> = repository
        .loadLevelsInfo()
        .map {
            LevelInfo(
                it.id,
                it.number,
                it.numberOfStars,
                it.numberOfBombs,
                it.numberOfFires,
                it.numberOfCells,
                it.activated
            )
        }
}