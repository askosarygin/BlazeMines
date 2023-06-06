package com.example.game_domain.usecases

import com.example.common.ScreenSettings
import com.example.game_domain.Repository
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