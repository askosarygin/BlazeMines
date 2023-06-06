package com.example.game_domain

import com.example.common.LevelInfoDB

interface Repository {
    suspend fun loadLevelsInfo() : List<LevelInfoDB>
}