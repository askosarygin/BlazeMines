package com.example.settings_domain

import com.example.common.ScreenSettings
import com.example.settings_domain.usecases.*
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val getBackgroundIdFromAppDataUseCase: GetBackgroundIdFromAppDataUseCase,
    private val getBombIconIdFromAppDataUseCase: GetBombIconIdFromAppDataUseCase,
    private val getFireIconIdFromAppDataUseCase: GetFireIconIdFromAppDataUseCase,
    private val saveBackgroundIdToAppDataUseCase: SaveBackgroundIdToAppDataUseCase,
    private val saveBombIconIdToAppDataUseCase: SaveBombIconIdToAppDataUseCase,
    private val saveFireIconIdToAppDataUseCase: SaveFireIconIdToAppDataUseCase,
    private val getScreenSettingsUseCase: GetScreenSettingsUseCase
) : Interactor {
    override suspend fun saveBackgroundIdToAppData(backgroundId: Int): Boolean =
        saveBackgroundIdToAppDataUseCase.execute(backgroundId)

    override suspend fun getBackgroundIdFromAppData(): Int =
        getBackgroundIdFromAppDataUseCase.execute()

    override suspend fun saveFireIconIdToAppData(fireIconId: Int): Boolean =
        saveFireIconIdToAppDataUseCase.execute(fireIconId)

    override suspend fun getFireIconIdFromAppData(): Int =
        getFireIconIdFromAppDataUseCase.execute()

    override suspend fun saveBombIconIdToAppData(bombIconId: Int): Boolean =
        saveBombIconIdToAppDataUseCase.execute(bombIconId)

    override suspend fun getBombIconIdFromAppData(): Int =
        getBombIconIdFromAppDataUseCase.execute()

    override suspend fun getScreenSettings(): ScreenSettings =
        getScreenSettingsUseCase.execute()

}