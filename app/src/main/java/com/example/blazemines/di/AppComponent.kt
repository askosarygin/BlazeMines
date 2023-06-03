package com.example.blazemines.di

import android.content.Context
import android.content.res.Resources
import com.example.blazemines.R
import com.example.common.NavHostsInfo
import com.example.game_ui.di.GameComponentDependencies
import com.example.main_ui.di.MainComponentDependencies
import com.example.settings_ui.di.SettingsComponentDependencies
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@[AppScope Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class
    ]
)]
interface AppComponent
    : GameComponentDependencies,
    MainComponentDependencies,
    SettingsComponentDependencies {

    override val navHostsInfo: NavHostsInfo
//    override val questionsInfoDAO: QuestionsInfoDAO
//    override val resources: Resources
//    override val sharedPreferences: SharedPreferences


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun resources(resources: Resources): Builder

        fun build(): AppComponent
    }
}

@Module
class AppModule {
    @Provides
    fun provideNavHostsInfo(): NavHostsInfo = NavHostsInfo(
        globalNavHostId = R.id.global_fragment_container_view
    )

//    @Provides
//    fun provideQuestionsInfoDB(
//        context: Context,
//        resources: Resources
//    ): QuestionsInfoDAO {
//        val db = Room.databaseBuilder(
//            context,
//            QuestionsInfoDatabase::class.java,
//            resources.getString(com.example.common.R.string.sports_quiz_database)
//        ).build()
//        return db.questionsInfoDAO()
//    }

//    @Provides
//    fun provideSharedPreferences(
//        context: Context,
//        resources: Resources
//    ): SharedPreferences =
//        context.getSharedPreferences(
//            resources.getString(com.example.common.R.string.sports_quiz_preferences),
//            Context.MODE_PRIVATE
//        )
}

@Module
interface AppModuleBinds {

//    @Binds
//    fun bindQuestionsInfoStorageImplToQuestionsInfoStorage(
//        questionsInfoStorageImpl: QuestionsInfoDBStorageImpl
//    ): QuestionsInfoDBStorage
}

@Scope
annotation class AppScope