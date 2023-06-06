package com.example.settings_domain

interface Repository {

    suspend fun saveBackgroundIdToAppData(backgroundId: Int) : Boolean

    suspend fun getBackgroundIdFromAppData(): Int

    suspend fun saveFireIconIdToAppData(fireIconId: Int) : Boolean

    suspend fun getFireIconIdFromAppData(): Int

    suspend fun saveBombIconIdToAppData(bombIconId: Int) : Boolean

    suspend fun getBombIconIdFromAppData(): Int
}