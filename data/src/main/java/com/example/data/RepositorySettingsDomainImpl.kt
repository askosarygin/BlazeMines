package com.example.data

import com.example.data.appdata.AppData
import com.example.settings_domain.Repository
import javax.inject.Inject

class RepositorySettingsDomainImpl @Inject constructor(
    private val appData: AppData
) : Repository {
    override suspend fun saveBackgroundIdToAppData(backgroundId: Int): Boolean =
        appData.saveBackgroundId(backgroundId)

    override suspend fun getBackgroundIdFromAppData(): Int =
        appData.getBackgroundId()

    override suspend fun saveFireIconIdToAppData(fireIconId: Int): Boolean =
        appData.saveFireIconId(fireIconId)

    override suspend fun getFireIconIdFromAppData(): Int =
        appData.getFireIconId()

    override suspend fun saveBombIconIdToAppData(bombIconId: Int): Boolean =
        appData.saveBombIconId(bombIconId)

    override suspend fun getBombIconIdFromAppData(): Int =
        appData.getBombIconId()

}