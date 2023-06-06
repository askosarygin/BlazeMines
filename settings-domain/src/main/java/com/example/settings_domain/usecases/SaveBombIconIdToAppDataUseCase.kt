package com.example.settings_domain.usecases

import com.example.settings_domain.Repository
import javax.inject.Inject

class SaveBombIconIdToAppDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(bombIconId: Int): Boolean =
        repository.saveBombIconIdToAppData(bombIconId)
}