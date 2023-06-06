package com.example.game_ui.screen_game

import androidx.appcompat.widget.AppCompatImageView

data class CellInfo(
    val cellView: AppCompatImageView,
    var isSelected: Boolean = false,
    var isFire: Boolean = false
)