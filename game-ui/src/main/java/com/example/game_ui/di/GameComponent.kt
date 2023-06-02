package com.example.game_ui.di

import androidx.lifecycle.ViewModel
import dagger.Component
import dagger.Module
import javax.inject.Scope
import kotlin.properties.Delegates

@[GameScope Component(
    dependencies = [GameComponentDependencies::class],
    modules = [
        GameModule::class,
        GameModuleBinds::class
    ]
)]
internal interface GameComponent {

//    fun inject(fragmentScreenDifficultySelection: FragmentScreenDifficultySelection)

    @Component.Builder
    interface Builder {

        fun dependencies(gameComponentDependencies: GameComponentDependencies): Builder

        fun build(): GameComponent
    }
}

@Module
class GameModule

@Module
interface GameModuleBinds {

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

interface GameComponentDependencies {
//    val navHostsInfo: NavHostsInfo
//    val questionsInfoDAO: QuestionsInfoDAO
//    val resources: Resources
//    val sharedPreferences: SharedPreferences
}

object GameComponentDependenciesStore {
    var dependencies: GameComponentDependencies by Delegates.notNull()
}

internal class GameComponentViewModel : ViewModel() {

    init {
        gameComponent = createComponent()
    }

    companion object {
        private var gameComponent: GameComponent? = null

        private fun createComponent(): GameComponent = gameComponent
            ?: DaggerGameComponent
                .builder()
                .dependencies(GameComponentDependenciesStore.dependencies)
                .build()

        fun getComponent(): GameComponent = gameComponent
            ?: throw RuntimeException("GameScreen component is not initialized")

        private fun closeComponent() {
            gameComponent = null
        }
    }

    override fun onCleared() {
        super.onCleared()

        closeComponent()
    }
}

@Scope
internal annotation class GameScope