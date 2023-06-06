package com.example.main_domain

import com.example.common.ScreenSettings

interface Interactor {
    suspend fun getScreenSettings(): ScreenSettings
}