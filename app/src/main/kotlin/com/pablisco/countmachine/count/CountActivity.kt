package com.pablisco.countmachine.count

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.pablisco.countmachine.R
import com.pablisco.countmachine.databinding.ActivityCountBinding
import com.pablisco.countmachine.di.appModule
import com.pablisco.countmachine.util.bindContentView
import com.pablisco.countmachine.util.onClick
import com.pablisco.countmachine.util.toggle
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

/**
 * Entry point for the application.
 */
class CountActivity : AppCompatActivity() {

    private val blinkAnimation by lazy { appModule.blinkAnimation }
    private val binding by lazy { bindContentView<ActivityCountBinding>(R.layout.activity_count) }
    private val viewModel by lazy { appModule.countModule.viewModel }

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        binding.onUpdate = ::update

        binding.totalView.onClick { toggle(blinkAnimation) }
    }

    override fun onResume() = super.onResume().also {
        update()
    }

    override fun onStop() = super.onStop().also {
        job?.cancel()
    }

    private fun update() {
        launch {
            viewModel.update(binding.inputValues)
            if (lifecycle.currentState >= Lifecycle.State.CREATED) {
                withContext(UI) {
                    binding.model = viewModel.model
                }
            }
        }
    }

}

/**
 * List of input fields
 */
private val ActivityCountBinding.inputFields
    get() = listOf(number1Input, number2Input, number3Input, number4Input, number5Input, number6Input)

/**
 * a list of available input values, disregarding not valid input values.
 */
private val ActivityCountBinding.inputValues
    get() = inputFields.mapNotNull(EditText::doubleValue)

/**
 * Either the double representation of the text of this EditText or null
 */
private val EditText.doubleValue: Double?
    get() = text.toString().toDoubleOrNull()
