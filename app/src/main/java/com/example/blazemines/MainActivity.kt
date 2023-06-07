package com.example.blazemines

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.blazemines.di.AppComponent
import com.example.blazemines.di.DaggerAppComponent
import com.example.data.db.LevelsInfoStorage
import com.example.game_ui.di.GameComponentDependenciesStore
import com.example.main_ui.di.MainComponentDependenciesStore
import com.example.settings_ui.di.SettingsComponentDependenciesStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .resources(resources)
            .build()

        GameComponentDependenciesStore.dependencies = appComponent
        MainComponentDependenciesStore.dependencies = appComponent
        SettingsComponentDependenciesStore.dependencies = appComponent

    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var db: LevelsInfoStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        lifecycleScope.launch(Dispatchers.IO) {
            db.initDB()
        }
    }
}