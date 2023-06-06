package com.example.data.db

import com.example.common.LevelInfoDB
import com.example.common.NumberOfStars
import javax.inject.Inject

class LevelsInfoStorageImpl @Inject constructor(
    private val levelsDatabase: LevelsDAO
) : LevelsInfoStorage {
    override suspend fun save(levelInfoDB: LevelInfoDB): Boolean {
        levelsDatabase.add(
            LevelDatabaseClass(
                levelInfoDB.id,
                levelInfoDB.number,
                levelInfoDB.numberOfStars,
                levelInfoDB.numberOfBombs,
                levelInfoDB.numberOfFires,
                levelInfoDB.numberOfCells,
                levelInfoDB.activated
            )
        )
        return true
    }

    override suspend fun getAll(): List<LevelInfoDB> {
        val levels = levelsDatabase.getAll()

        if (levels.isEmpty()) {
            DefaultLevelsList.levels.forEach {
                save(it)
            }

            return levelsDatabase.getAll().map {
                LevelInfoDB(
                    it.id,
                    it.number,
                    it.numberOfStars,
                    it.numberOfBombs,
                    it.numberOfFires,
                    it.numberOfCells,
                    it.activated
                )
            }
        }

        return levels.map {
            LevelInfoDB(
                it.id,
                it.number,
                it.numberOfStars,
                it.numberOfBombs,
                it.numberOfFires,
                it.numberOfCells,
                it.activated
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

    override suspend fun updateCellActivation(id: Long, activated: Boolean): Boolean {
        val level = levelsDatabase.getLevelById(id)

        levelsDatabase.update(
            level.copy(
                activated = activated
            )
        )
        return true
    }
}