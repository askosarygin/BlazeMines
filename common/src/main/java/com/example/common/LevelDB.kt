package com.example.common

data class LevelDB(
    val id: Long,
    val number: Int,
    val numberOfStars: String,
    val numberOfBombs: Int,
    val numberOfFires: Int,
    val numberOfCells: Int,
    val activated: Boolean
)
