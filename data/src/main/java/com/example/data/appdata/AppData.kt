package com.example.data.appdata

interface AppData {
    fun saveBackgroundId(backgroundId: Int) : Boolean

    fun getBackgroundId(): Int

    fun saveFireIconId(fireIconId: Int) : Boolean

    fun getFireIconId(): Int

    fun saveBombIconId(bombIconId: Int) : Boolean

    fun getBombIconId(): Int
}