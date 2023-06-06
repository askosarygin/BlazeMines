package com.example.game_domain.usecases

import com.example.common.LevelInfo
import com.example.common.NumberOfStars
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
                convertNumberOfStars(it.numberOfStars),
                it.numberOfBombs,
                it.numberOfFires,
                it.numberOfCells,
                it.activated
            )
        }

    private fun convertNumberOfStars(numberOfStars: String): Int =
        when (numberOfStars) {
            NumberOfStars.Zero.name -> 0
            NumberOfStars.One.name -> 1
            NumberOfStars.Two.name -> 2
            NumberOfStars.Three.name -> 3
            else -> -1
        }
}