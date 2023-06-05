package com.example.data.db

import com.example.common.LevelDB
import com.example.common.NumberOfStars

object DefaultLevelsList {
    val levels = listOf(
        LevelDB(0L, 1, NumberOfStars.Zero.name, 2, 1, 3, false),
        LevelDB(0L, 2, NumberOfStars.Zero.name, 3, 2, 5, false),
        LevelDB(0L, 3, NumberOfStars.Zero.name, 4, 2, 6, false),
        LevelDB(0L, 4, NumberOfStars.Zero.name, 5, 2, 7, false),
        LevelDB(0L, 5, NumberOfStars.Zero.name, 6, 3, 8, false),
        LevelDB(0L, 6, NumberOfStars.Zero.name, 7, 3, 10, false),
        LevelDB(0L, 7, NumberOfStars.Zero.name, 8, 3, 11, false),
        LevelDB(0L, 8, NumberOfStars.Zero.name, 9, 4, 13, false),
        LevelDB(0L, 9, NumberOfStars.Zero.name, 9, 5, 14, false),
        LevelDB(0L, 10, NumberOfStars.Zero.name, 9, 6, 15, false),
        LevelDB(0L, 11, NumberOfStars.Zero.name, 10, 5, 15, false),
        LevelDB(0L, 12, NumberOfStars.Zero.name, 8, 7, 15, false),
        LevelDB(0L, 13, NumberOfStars.Zero.name, 8, 7, 15, false),
        LevelDB(0L, 14, NumberOfStars.Zero.name, 7, 8, 15, false),
        LevelDB(0L, 15, NumberOfStars.Zero.name, 6, 9, 15, false)
    )
}