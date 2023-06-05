package com.example.data.db

import com.example.common.LevelDB
import com.example.common.NumberOfStars

interface LevelsStorage {
    suspend fun save(levelDB: LevelDB) : Boolean

    suspend fun getAll() : List<LevelDB>

    suspend fun updateNumberOfStars(id: Long, newNumberOfStars: NumberOfStars) : Boolean
}