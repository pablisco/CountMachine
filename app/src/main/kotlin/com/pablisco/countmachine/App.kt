package com.pablisco.countmachine

import android.app.Application
import android.content.Context
import android.view.animation.Animation
import com.pablisco.countmachine.di.AppModule
import com.pablisco.countmachine.di.AppModuleSource
import com.pablisco.countmachine.di.CountModule
import com.pablisco.countmachine.util.lazyAnimation

@Suppress("unused") // Registered in the manifest
class App : Application(), AppModuleSource {

    override val appModule: AppModule by lazy { ProductionAppModule(this) }

}

private class ProductionAppModule(context: Context) : AppModule {
    override val countModule = object : CountModule {
        override val viewModel get() = CountViewModel()
    }
    override val blinkAnimation: Animation by context.lazyAnimation(R.anim.blink)
}
