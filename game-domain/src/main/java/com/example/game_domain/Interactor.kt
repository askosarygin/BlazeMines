package com.example.game_domain

import com.example.common.LevelInfo

interface Interactor {
    suspend fun loadLevelsInfoFromDB(): List<LevelInfo>
}