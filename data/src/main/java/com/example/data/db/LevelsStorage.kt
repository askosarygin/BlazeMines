package com.example.data.db

import com.example.common.LevelInfoDB
import com.example.common.NumberOfStars

interface LevelsStorage {
    suspend fun save(levelInfoDB: LevelInfoDB) : Boolean

    suspend fun getAll() : List<LevelInfoDB>

    suspend fun updateNumberOfStars(id: Long, newNumberOfStars: NumberOfStars) : Boolean
}