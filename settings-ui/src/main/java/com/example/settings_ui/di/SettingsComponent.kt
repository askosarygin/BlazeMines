package com.example.settings_ui.di

import androidx.lifecycle.ViewModel
import dagger.Component
import dagger.Module
import javax.inject.Scope
import kotlin.properties.Delegates

@[SettingsScope Component(
    dependencies = [SettingsComponentDependencies::class],
    modules = [
        SettingsModule::class,
        SettingsModuleBinds::class
    ]
)]
internal interface SettingsComponent {

//    fun inject(fragmentScreenDifficultySelection: FragmentScreenDifficultySelection)

    @Component.Builder
    interface Builder {

        fun dependencies(settingsComponentDependencies: SettingsComponentDependencies): Builder

        fun build(): SettingsComponent
    }
}

@Module
class SettingsModule

@Module
interface SettingsModuleBinds {

//    @Binds
//    fun bindRepositoryGameScreenDomainImplToRepository(
//        repositoryGameScreenDomainImpl: RepositoryGameScreenDomainImpl
//    ): Repository
//
//    @Binds
//    fun bindInteractorImplToInteractor(
//        interactorImpl: InteractorImpl
//    ): Interactor
//
//    @Binds
//    fun bindQuestionsInfoDBStorageImplToQuestionsInfoDBStorage(
//        questionsInfoDBStorageImpl: QuestionsInfoDBStorageImpl
//    ): QuestionsInfoDBStorage
//
//    @Binds
//    fun bindAccountDataStorageImplToAccountDataStorage(
//        accountDataStorageImpl: AccountDataStorageImpl
//    ): AccountDataStorage
}

interface SettingsComponentDependencies {
//    val navHostsInfo: NavHostsInfo
//    val questionsInfoDAO: QuestionsInfoDAO
//    val resources: Resources
//    val sharedPreferences: SharedPreferences
}

object SettingsComponentDependenciesStore {
    var dependencies: SettingsComponentDependencies by Delegates.notNull()
}

internal class SettingsComponentViewModel : ViewModel() {

    init {
        settingsComponent = createComponent()
    }

    companion object {
        private var settingsComponent: SettingsComponent? = null

        private fun createComponent(): SettingsComponent = settingsComponent
            ?: DaggerSettingsComponent
                .builder()
                .dependencies(SettingsComponentDependenciesStore.dependencies)
                .build()

        fun getComponent(): SettingsComponent = settingsComponent
            ?: throw RuntimeException("GameScreen component is not initialized")

        private fun closeComponent() {
            settingsComponent = null
        }
    }

    override fun onCleared() {
        super.onCleared()

        closeComponent()
    }
}

@Scope
internal annotation class SettingsScope