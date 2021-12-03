package ru.endroad.hakaton.mlcv.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View

fun View.fadeOut(hiddenVisibility: Int = View.GONE, startAnimation: Boolean = true): Animator {
	val animator = ObjectAnimator.ofFloat(this, View.ALPHA, 0f)
	animator.addListener(object : AnimatorListenerAdapter() {
		override fun onAnimationEnd(animation: Animator?) {
			visibility = hiddenVisibility
		}
	})
	if (startAnimation) {
		animator.start()
	}
	return animator
}

fun View.fadeIn(startAnimation: Boolean = true): Animator {
	val animator = ObjectAnimator.ofFloat(this, View.ALPHA, 1f)
	animator.addListener(object : AnimatorListenerAdapter() {
		override fun onAnimationStart(animation: Animator?) {
			visibility = View.VISIBLE
		}
	})
	if (startAnimation) {
		animator.start()
	}
	return animator
}

