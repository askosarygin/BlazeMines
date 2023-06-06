package com.example.blazemines.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.room.Room
import com.example.blazemines.R
import com.example.common.NavHostsInfo
import com.example.data.db.LevelsDAO
import com.example.data.db.LevelsInfoDatabase
import com.example.data.db.LevelsInfoStorage
import com.example.data.db.LevelsInfoStorageImpl
import com.example.game_ui.di.GameComponentDependencies
import com.example.main_ui.di.MainComponentDependencies
import com.example.settings_ui.di.SettingsComponentDependencies
import dagger.*
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
    override val levelsInfoStorage: LevelsInfoStorage
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

    @Provides
    fun provideLevelsInfoDB(
        context: Context,
        resources: Resources
    ): LevelsDAO {
        val db = Room.databaseBuilder(
            context,
            LevelsInfoDatabase::class.java,
            resources.getString(com.example.common.R.string.blaze_mines_database)
        ).build()

        return db.levelsDAO()
    }

    @Provides
    fun provideSharedPreferences(
        context: Context,
        resources: Resources
    ): SharedPreferences =
        context.getSharedPreferences(
            resources.getString(com.example.common.R.string.blaze_mines_preferences),
            Context.MODE_PRIVATE
        )
}

@Module
interface AppModuleBinds {

    @Binds
    fun bindLevelsInfoStorageImplToLevelsInfoStorage(
        levelsInfoStorageImpl: LevelsInfoStorageImpl
    ): LevelsInfoStorage
}

@Scope
annotation class AppScope