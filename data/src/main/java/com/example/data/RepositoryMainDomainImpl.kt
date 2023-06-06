package com.example.data

import com.example.common.LevelInfoDB
import com.example.data.db.LevelsInfoStorage
import com.example.game_domain.Repository
import javax.inject.Inject

class RepositoryMainDomainImpl @Inject constructor(
    private val levelsInfoStorage: LevelsInfoStorage
) : Repository {
    override suspend fun loadLevelsInfo(): List<LevelInfoDB> = levelsInfoStorage.getAll()
}