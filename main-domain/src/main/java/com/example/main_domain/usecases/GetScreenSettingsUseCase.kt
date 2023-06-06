package com.example.main_domain.usecases

import com.example.common.ScreenSettings
import com.example.main_domain.Repository
import javax.inject.Inject

class GetScreenSettingsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): ScreenSettings = ScreenSettings(
        repository.getBackgroundIdFromAppData(),
        repository.getFireIconIdFromAppData(),
        repository.getBombIconIdFromAppData()
    )
}