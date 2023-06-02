package com.example.blazemines

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blazemines.di.AppComponent
import com.example.blazemines.di.DaggerAppComponent
import com.example.game_ui.di.GameComponentDependenciesStore
import com.example.main_ui.di.MainComponentDependenciesStore
import com.example.settings_ui.di.SettingsComponentDependenciesStore

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}