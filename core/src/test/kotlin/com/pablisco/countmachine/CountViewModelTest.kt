package com.pablisco.countmachine

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountViewModelTest {

    @Test
    fun `Initial state`() {
        val viewModel = CountViewModel()

        assertThat(viewModel.model).isEqualTo(CountModel(
            total = "",
            totalDescription = ""
        ))
    }

    @Test
    fun `Empty values`() {
        val viewModel = CountViewModel().apply { update(emptyList()) }

        assertThat(viewModel.model).isEqualTo(CountModel(
            total = "",
            totalDescription = ""
        ))
    }

    @Test
    fun `Single value without decimals`() {
        val viewModel = CountViewModel().apply { update(listOf(42.0)) }

        assertThat(viewModel.model).isEqualTo(CountModel(
            total = "42",
            totalDescription = ""
        ))
    }

    @Test
    fun `Single value with decimals`() {
        val viewModel = CountViewModel().apply { update(listOf(42.64)) }

        assertThat(viewModel.model).isEqualTo(CountModel(
            total = "42.64",
            totalDescription = ""
        ))
    }

    @Test
    fun `Multiple values without decimals`() {
        val viewModel = CountViewModel().apply { update(listOf(42.0, 12.0)) }

        assertThat(viewModel.model).isEqualTo(CountModel(
            total = "54",
            totalDescription = "42+12="
        ))
    }

    @Test
    fun `Multiple values with decimals`() {
        val viewModel = CountViewModel().apply { update(listOf(42.64, 12.0)) }

        assertThat(viewModel.model).isEqualTo(CountModel(
            total = "54.64",
            totalDescription = "42.64+12="
        ))
    }

    @Test
    fun `Values round to two decimal places`() {
        val viewModel = CountViewModel().apply { update(listOf(42.645, 12.644)) }

        assertThat(viewModel.model).isEqualTo(CountModel(
            total = "55.29",
            totalDescription = "42.65+12.64="
        ))
    }

}