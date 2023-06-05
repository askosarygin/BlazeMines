package com.example.data.db

import com.example.common.LevelDB
import com.example.common.NumberOfStars
import javax.inject.Inject

class LevelsStorageImpl @Inject constructor(
    private val levelsDatabase: LevelsDAO
) : LevelsStorage {
    override suspend fun save(levelDB: LevelDB): Boolean {
        levelsDatabase.add(
            LevelDatabaseClass(
                levelDB.id,
                levelDB.number,
                levelDB.numberOfStars,
                levelDB.numberOfBombs,
                levelDB.numberOfFires,
                levelDB.numberOfCells
            )
        )
        return true
    }

    override suspend fun getAll(): List<LevelDB> {
        val levels = levelsDatabase.getAll()

        if (levels.isEmpty()) {
            DefaultLevelsList.levels.forEach {
                save(it)
            }

            return levelsDatabase.getAll().map {
                LevelDB(
                    it.id,
                    it.number,
                    it.numberOfStars,
                    it.numberOfBombs,
                    it.numberOfFires,
                    it.numberOfCells
                )
            }
        }

        return levels.map {
            LevelDB(
                it.id,
                it.number,
                it.numberOfStars,
                it.numberOfBombs,
                it.numberOfFires,
                it.numberOfCells
            )
        }
    }

    override suspend fun updateNumberOfStars(id: Long, newNumberOfStars: NumberOfStars): Boolean {
        val level = levelsDatabase.getLevelById(id)
        levelsDatabase.update(
            level.copy(
                numberOfStars = newNumberOfStars.name
            )
        )
        return true
    }
}