package com.example.settings_domain.usecases

import com.example.settings_domain.Repository
import javax.inject.Inject

class SaveBackgroundIdToAppDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(backgroundId: Int): Boolean =
        repository.saveBackgroundIdToAppData(backgroundId)
}