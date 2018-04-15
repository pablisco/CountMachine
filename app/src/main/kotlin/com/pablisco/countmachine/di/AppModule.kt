package com.pablisco.countmachine.di

import android.content.Context
import android.view.animation.Animation

/**
 * Root module
 */
interface AppModule {
    val countModule: CountModule
    val blinkAnimation : Animation

}

/**
 * Used by the relevant [android.app.Application] to provide dependencies
 */
interface AppModuleSource {
    val appModule: AppModule
}

/**
 * Point of injection, if a component has access to a context they can access this property.
 */
val Context.appModule: AppModule
    get() = applicationContext
        .let { it as? AppModuleSource }?.appModule
        ?: error("$applicationContext must extend AppModuleSource")