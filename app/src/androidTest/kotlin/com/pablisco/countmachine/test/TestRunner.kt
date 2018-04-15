package com.pablisco.countmachine.test

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.pablisco.countmachine.TestApp

class TestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }

}