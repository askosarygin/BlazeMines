package com.example.main_domain

interface Repository {

    suspend fun getBackgroundIdFromAppData(): Int

    suspend fun getFireIconIdFromAppData(): Int

    suspend fun getBombIconIdFromAppData(): Int
}