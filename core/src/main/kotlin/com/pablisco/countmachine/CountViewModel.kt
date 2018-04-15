package com.pablisco.countmachine

import java.text.DecimalFormat

/**
 * State of the count and commands.
 */
class CountViewModel {

    private var _model = CountModel("", "")
    val model get() = _model

    fun update(values: List<Double>) {
        _model = CountModel(
            total = values.total(),
            totalDescription = values.totalDescription()
        )
    }

}

private fun List<Double>.totalDescription() = when {
    size <= 1 -> ""
    else -> joinToString(separator = "+", postfix = "=") { totalTemplate.format(it) }
}

private fun List<Double>.total() = when {
    isEmpty() -> ""
    else -> totalTemplate.format(sum())
}

private val totalTemplate = DecimalFormat("#.##")
