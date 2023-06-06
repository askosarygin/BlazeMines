package com.example.data

import com.example.common.LevelInfoDB
import com.example.common.NumberOfStars
import com.example.data.appdata.AppData
import com.example.data.db.LevelsInfoStorage
import com.example.game_domain.Repository
import javax.inject.Inject

class RepositoryGameDomainImpl @Inject constructor(
    private val levelsInfoStorage: LevelsInfoStorage,
    private val appData: AppData
) : Repository {
    override suspend fun loadLevelsInfo(): List<LevelInfoDB> =
        levelsInfoStorage.getAll()

    override suspend fun updateNumberOfStarsInDB(
        id: Long,
        newNumberOfStars: NumberOfStars
    ): Boolean =
        levelsInfoStorage.updateNumberOfStars(
            id,
            newNumberOfStars
        )

    override suspend fun updateCellActivationInDB(id: Long, activated: Boolean): Boolean =
        levelsInfoStorage.updateCellActivation(
            id,
            activated
        )

    override suspend fun getBackgroundIdFromAppData(): Int =
        appData.getBackgroundId()

    override suspend fun getFireIconIdFromAppData(): Int =
        appData.getFireIconId()

    override suspend fun getBombIconIdFromAppData(): Int =
        appData.getBombIconId()
}