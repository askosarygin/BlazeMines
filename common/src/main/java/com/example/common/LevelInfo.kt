package com.example.common

data class LevelInfo(
    val id: Long = 0L,
    val number: Int = 0,
    val numberOfStars: Int = 0,
    val numberOfBombs: Int = 0,
    val numberOfFires: Int = 0,
    val numberOfCells: Int = 0,
    val activated: Boolean = false
) : java.io.Serializable
