package com.example.main_ui.di

import androidx.lifecycle.ViewModel
import dagger.Component
import dagger.Module
import javax.inject.Scope
import kotlin.properties.Delegates

@[MainScope Component(
    dependencies = [MainComponentDependencies::class],
    modules = [
        MainModule::class,
        MainModuleBinds::class
    ]
)]
internal interface MainComponent {

//    fun inject(fragmentScreenDifficultySelection: FragmentScreenDifficultySelection)

    @Component.Builder
    interface Builder {

        fun dependencies(mainComponentDependencies: MainComponentDependencies): Builder

        fun build(): MainComponent
    }
}

@Module
class MainModule

@Module
interface MainModuleBinds {

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

interface MainComponentDependencies {
//    val navHostsInfo: NavHostsInfo
//    val questionsInfoDAO: QuestionsInfoDAO
//    val resources: Resources
//    val sharedPreferences: SharedPreferences
}

object MainComponentDependenciesStore {
    var dependencies: MainComponentDependencies by Delegates.notNull()
}

internal class MainComponentViewModel : ViewModel() {

    init {
        mainComponent = createComponent()
    }

    companion object {
        private var mainComponent: MainComponent? = null

        private fun createComponent(): MainComponent = mainComponent
            ?: DaggerMainComponent
                .builder()
                .dependencies(MainComponentDependenciesStore.dependencies)
                .build()

        fun getComponent(): MainComponent = mainComponent
            ?: throw RuntimeException("GameScreen component is not initialized")

        private fun closeComponent() {
            mainComponent = null
        }
    }

    override fun onCleared() {
        super.onCleared()

        closeComponent()
    }
}

@Scope
internal annotation class MainScope