package com.example.common

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.Serializable

open class BlazeMinesFragment(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {

    protected fun <MODEL> Flow<MODEL>.collectWithOld(
        scope: CoroutineScope,
        action: suspend (oldModel: MODEL?, newModel: MODEL) -> Unit
    ) {
        var oldModel: MODEL? = null
        onEach { newModel ->
            action(oldModel, newModel)
            oldModel = newModel
        }.launchIn(scope)
    }

    protected fun navigateToModuleScreen(
        moduleName: ModuleNames,
        screenNames: ScreenNames,
        @IdRes navHostId: Int,
        navOptions: NavOptions = androidx.navigation.navOptions {
            anim {
                enter = R.anim.nav_default_enter_anim
                popEnter = R.anim.nav_default_pop_enter_anim
                popExit = R.anim.nav_default_pop_exit_anim
                exit = R.anim.nav_default_exit_anim
            }
        }
    ) {
        val deepLink = when (moduleName) {
            ModuleNames.Game -> resources.getString(com.example.common.R.string.deep_link_game_module_without_argument)
            ModuleNames.Main -> resources.getString(com.example.common.R.string.deep_link_main_module_without_argument)
            ModuleNames.Settings -> resources.getString(com.example.common.R.string.deep_link_settings_module_without_argument)
        }

        Navigation.findNavController(
            requireActivity(),
            navHostId
        ).navigate(
            Uri.parse(
                buildString {
                    append(deepLink)
                    append(screenNames)
                }
            ),
            navOptions
        )
    }

    protected fun navigateToActionId(
        @IdRes actionId: Int,
        bundle: Serializable? = null,
        bundleKey: String = "",
        navOptions: NavOptions = androidx.navigation.navOptions {
            anim {
                enter = R.anim.nav_default_enter_anim
                popEnter = R.anim.nav_default_pop_enter_anim
                popExit = R.anim.nav_default_pop_exit_anim
                exit = R.anim.nav_default_exit_anim
            }
        }
    ) {
        val sentBundle = Bundle().apply { putSerializable(bundleKey, bundle) }

        findNavController().navigate(actionId, sentBundle, navOptions)
    }

    protected fun getBundleNavigation(bundleKey: String): Serializable =
        arguments?.getSerializable(bundleKey) ?: throw RuntimeException("Bundle is empty!")

    @SuppressLint("UseCompatLoadingForDrawables")
    protected fun changeScreenSettings(
        screenSettings: ScreenSettings,
        root: ConstraintLayout? = null,
        changeBombIcon: (Drawable) -> Unit = {},
        changeFireIcon: (Drawable) -> Unit = {},
    ) {
        root?.let {
            when (screenSettings.backgroundId) {
                1 -> root.setBackgroundColor(resources.getColor(com.example.common.R.color.blaze_mines_black_2))
                2 -> root.background =
                    resources.getDrawable(com.example.common.R.drawable.background_2)
                3 -> root.background =
                    resources.getDrawable(com.example.common.R.drawable.background_3)
                4 -> root.background =
                    resources.getDrawable(com.example.common.R.drawable.background_4)
            }
        }

        val bombIcon = when (screenSettings.bombIconId) {
            1 -> resources.getDrawable(com.example.common.R.drawable.icon_cell_bomb_1)
            2 -> resources.getDrawable(com.example.common.R.drawable.icon_cell_bomb_2)
            else -> resources.getDrawable(com.example.common.R.drawable.icon_cell_bomb_1)
        }
        changeBombIcon(bombIcon)

        val fireIcon = when (screenSettings.fireIconId) {
            1 -> resources.getDrawable(com.example.common.R.drawable.icon_cell_fire_1)
            2 -> resources.getDrawable(com.example.common.R.drawable.icon_cell_fire_2)
            else -> resources.getDrawable(com.example.common.R.drawable.icon_cell_fire_1)
        }
        changeFireIcon(fireIcon)
    }
}