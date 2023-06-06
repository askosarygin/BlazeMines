package com.example.game_domain.usecases

import com.example.common.NumberOfStars
import com.example.game_domain.Repository
import javax.inject.Inject

class UpdateNumberOfStarsInDBUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(id: Long, newNumberOfStars: NumberOfStars) : Boolean = repository
        .updateNumberOfStarsInDB(id, newNumberOfStars)
}