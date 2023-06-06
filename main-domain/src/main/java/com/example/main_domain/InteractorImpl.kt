package com.example.main_domain

import com.example.common.ScreenSettings
import com.example.main_domain.usecases.GetScreenSettingsUseCase
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val getScreenSettingsUseCase: GetScreenSettingsUseCase
) : Interactor {
    override suspend fun getScreenSettings(): ScreenSettings =
        getScreenSettingsUseCase.execute()

}