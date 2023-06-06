package com.example.game_ui.common

import com.example.common.LevelInfo

data class LevelsInfo(
    val currentLevelIndex: Int = -1,
    val nextLevelIndex: Int = -1,
    val levelsInfo: List<LevelInfo> = listOf()
) : java.io.Serializable