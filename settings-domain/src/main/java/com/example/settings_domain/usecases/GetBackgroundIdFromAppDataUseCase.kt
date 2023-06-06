package com.example.settings_domain.usecases

import com.example.settings_domain.Repository
import javax.inject.Inject

class GetBackgroundIdFromAppDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): Int = repository.getBackgroundIdFromAppData()
}