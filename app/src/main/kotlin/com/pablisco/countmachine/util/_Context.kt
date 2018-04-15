package com.pablisco.countmachine.util

import android.content.Context
import android.support.annotation.AnimRes
import android.view.animation.AnimationUtils

fun Context.lazyAnimation(@AnimRes id: Int) =
    lazy { AnimationUtils.loadAnimation(this, id) }