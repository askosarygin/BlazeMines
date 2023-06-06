package com.example.data

import com.example.data.appdata.AppData
import com.example.main_domain.Repository
import javax.inject.Inject

class RepositoryMainDomainImpl @Inject constructor(
    private val appData: AppData
) : Repository {

    override suspend fun getBackgroundIdFromAppData(): Int =
        appData.getBackgroundId()

    override suspend fun getFireIconIdFromAppData(): Int =
        appData.getFireIconId()

    override suspend fun getBombIconIdFromAppData(): Int =
        appData.getBombIconId()

}