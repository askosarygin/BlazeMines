package com.example.game_ui.common

import com.example.common.LevelInfo

data class LevelResult(
    val levelName: String = "",
    val foundFires: Int = -1,
    val needFires: Int = -1,
    val starsCount: Int = -1,
    val currentLevelInfo: LevelInfo = LevelInfo(),
    val levelsInfo: LevelsInfo = LevelsInfo()
) : java.io.Serializable