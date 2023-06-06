package com.example.data

import com.example.common.LevelInfoDB
import com.example.common.NumberOfStars
import com.example.data.db.LevelsInfoStorage
import com.example.game_domain.Repository
import javax.inject.Inject

class RepositoryGameDomainImpl @Inject constructor(
    private val levelsInfoStorage: LevelsInfoStorage
) : Repository {
    override suspend fun loadLevelsInfo(): List<LevelInfoDB> = levelsInfoStorage.getAll()

    override suspend fun updateNumberOfStarsInDB(
        id: Long,
        newNumberOfStars: NumberOfStars
    ): Boolean = levelsInfoStorage.updateNumberOfStars(id, newNumberOfStars)
}