package com.example.game_domain

import com.example.common.LevelInfo
import com.example.common.NumberOfStars
import com.example.common.ScreenSettings

interface Interactor {
    suspend fun loadLevelsInfoFromDB(): List<LevelInfo>

    suspend fun updateNumberOfStarsInDB(id: Long, newNumberOfStars: NumberOfStars): Boolean

    suspend fun updateCellActivationInDB(id: Long, activated: Boolean): Boolean

    suspend fun getScreenSettings(): ScreenSettings
}