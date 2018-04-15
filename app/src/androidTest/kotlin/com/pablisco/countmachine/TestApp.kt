package com.pablisco.countmachine

import android.app.Application
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.pablisco.countmachine.di.AppModule
import com.pablisco.countmachine.di.AppModuleSource
import com.pablisco.countmachine.di.CountModule
import org.junit.rules.ExternalResource

/**
 * Instrumentation tests will use this as the application so we can inject our own dependencies.
 *
 * Due to an issue with Mockito, this just replaces the animation with one that will have no
 * effects on the tests (even if in this case there should be no problem)
 */
class TestApp : Application(), AppModuleSource {

    companion object {
        var testAppModule: TestAppModule = TestAppModule()
    }

    /**
     * Each test should provide their own
     */
    override val appModule: AppModule
        get() = testAppModule


}

class TestAppModule : AppModule {
    override var countModule: CountModule = TestCountModule()
    override var blinkAnimation: Animation = AlphaAnimation(1f, 1f)
}

class TestCountModule : CountModule {
    // TODO: Inject mock
    override var viewModel: CountViewModel = CountViewModel()
}

/**
 * Used to inject a local [AppModule] that can be mutated on a test and restores a default after the test.
 */

@Suppress("unused", "MemberVisibilityCanBePrivate") // For future use
class TestAppRule : ExternalResource() {

    val appModule = TestAppModule()

    override fun before() {
        TestApp.testAppModule = appModule
    }

    override fun after() {
        TestApp.testAppModule = TestAppModule()
    }

}