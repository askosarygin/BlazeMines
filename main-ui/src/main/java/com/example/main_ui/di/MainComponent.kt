package com.example.main_ui.di

import androidx.lifecycle.ViewModel
import com.example.common.NavHostsInfo
import com.example.data.RepositoryMainDomainImpl
import com.example.data.appdata.AppData
import com.example.main_domain.Interactor
import com.example.main_domain.InteractorImpl
import com.example.main_domain.Repository
import com.example.main_ui.screen_how_to_play.FragmentScreenHowToPlay
import com.example.main_ui.screen_start.FragmentScreenStart
import dagger.Binds
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

    fun inject(fragmentScreenStart: FragmentScreenStart)
    fun inject(fragmentScreenHowToPlay: FragmentScreenHowToPlay)

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

    @Binds
    fun bindRepositoryMainDomainImplToRepository(
        repositoryMainDomainImpl: RepositoryMainDomainImpl
    ): Repository

    @Binds
    fun bindInteractorImplToInteractor(
        interactorImpl: InteractorImpl
    ): Interactor
}

interface MainComponentDependencies {
    val navHostsInfo: NavHostsInfo
    val appData: AppData
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
            ?: throw RuntimeException("Main component is not initialized")

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