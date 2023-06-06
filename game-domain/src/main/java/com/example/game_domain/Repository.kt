package com.example.game_domain

import com.example.common.LevelInfoDB
import com.example.common.NumberOfStars

interface Repository {
    suspend fun loadLevelsInfo() : List<LevelInfoDB>

    suspend fun updateNumberOfStarsInDB(id: Long, newNumberOfStars: NumberOfStars) : Boolean
}