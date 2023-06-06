package com.example.settings_domain.usecases

import com.example.settings_domain.Repository
import javax.inject.Inject

class SaveFireIconIdToAppDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(fireIconId: Int): Boolean =
        repository.saveFireIconIdToAppData(fireIconId)
}