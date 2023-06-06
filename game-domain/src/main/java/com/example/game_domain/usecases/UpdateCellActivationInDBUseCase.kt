package com.example.game_domain.usecases

import com.example.game_domain.Repository
import javax.inject.Inject

class UpdateCellActivationInDBUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(id: Long, activated: Boolean) : Boolean = repository
        .updateCellActivationInDB(id, activated)
}