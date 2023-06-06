package com.example.settings_domain

import com.example.common.ScreenSettings

interface Interactor {
    suspend fun saveBackgroundIdToAppData(backgroundId: Int) : Boolean

    suspend fun getBackgroundIdFromAppData(): Int

    suspend fun saveFireIconIdToAppData(fireIconId: Int) : Boolean

    suspend fun getFireIconIdFromAppData(): Int

    suspend fun saveBombIconIdToAppData(bombIconId: Int) : Boolean

    suspend fun getBombIconIdFromAppData(): Int

    suspend fun getScreenSettings(): ScreenSettings
}