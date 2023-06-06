package com.example.data

import com.example.common.LevelInfoDB
import com.example.data.db.LevelsStorage
import com.example.game_domain.Repository
import javax.inject.Inject

class RepositoryMainDomainImpl @Inject constructor(
    private val levelsStorage: LevelsStorage
) : Repository {
    override suspend fun loadLevelsInfo(): List<LevelInfoDB> = levelsStorage.getAll()
}