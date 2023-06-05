package com.example.data.appdata

import android.content.SharedPreferences
import javax.inject.Inject

class AppDataImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AppData {
    private val keyBackgroundId = "background_id"
    private val keyFireIconId = "fire_icon_id"
    private val keyBombIconId = "bomb_icon_id"

    override fun saveBackgroundId(backgroundId: Int): Boolean {
        sharedPreferences.edit().putInt(keyBackgroundId, backgroundId).apply()
        return true
    }

    override fun getBackgroundId(): Int = sharedPreferences.getInt(keyBackgroundId, -1)

    override fun saveFireIconId(fireIconId: Int): Boolean {
        sharedPreferences.edit().putInt(keyFireIconId, fireIconId).apply()
        return true
    }

    override fun getFireIconId(): Int = sharedPreferences.getInt(keyFireIconId, -1)

    override fun saveBombIconId(bombIconId: Int): Boolean {
        sharedPreferences.edit().putInt(keyBombIconId, bombIconId).apply()
        return true
    }

    override fun getBombIconId(): Int = sharedPreferences.getInt(keyBombIconId, -1)

}