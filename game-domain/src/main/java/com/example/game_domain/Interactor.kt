package com.example.game_domain

import com.example.common.LevelInfo
import com.example.common.NumberOfStars

interface Interactor {
    suspend fun loadLevelsInfoFromDB(): List<LevelInfo>

    suspend fun updateNumberOfStars(id: Long, newNumberOfStars: NumberOfStars): Boolean

    suspend fun updateCellActivation(id: Long, activated: Boolean): Boolean
}