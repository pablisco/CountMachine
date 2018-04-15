package com.pablisco.countmachine.util

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding

fun <A : ViewDataBinding> Activity.bindContentView(id: Int): A =
    DataBindingUtil.setContentView(this, id)