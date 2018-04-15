package com.pablisco.countmachine.util

import android.view.View
import android.view.animation.Animation

/**
 * Same as [View.setOnClickListener] but with view as the receiver.
 */
fun View.onClick(block: View.() -> Unit) =
    setOnClickListener(block)


/**
 * Starts [new] if not present in view, otherwise it gets cancelled.
 */
fun View.toggle(new: Animation) = when (animation) {
    new -> clearAnimation()
    else -> startAnimation(new)
}