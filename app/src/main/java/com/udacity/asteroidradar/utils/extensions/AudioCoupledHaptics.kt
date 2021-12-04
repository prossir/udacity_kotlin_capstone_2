package com.udacity.asteroidradar.utils.extensions

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager


// HAPTIC FEEDBACK RELATED
private const val DEFAULT_VIBRATION_DURATION = 1000L

private val Context.vibrator: Vibrator
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

private fun Context.vibrateIfPossibleForError() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val effect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            VibrationEffect.createOneShot(DEFAULT_VIBRATION_DURATION, VibrationEffect.EFFECT_HEAVY_CLICK)
        } else {
            VibrationEffect.createOneShot(DEFAULT_VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE)
        }
        vibrator.cancel()
        vibrator.vibrate(effect)
    }
}

private fun Context.vibrateIfPossibleForSuccess() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val effect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            VibrationEffect.createOneShot(DEFAULT_VIBRATION_DURATION, VibrationEffect.EFFECT_TICK)
        } else {
            VibrationEffect.createOneShot(DEFAULT_VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE)
        }
        vibrator.cancel()
        vibrator.vibrate(effect)
    }
}

// SOUND RELATED
const val NEGATIVE_TONE = ToneGenerator.TONE_CDMA_PIP
const val POSITIVE_TONE = ToneGenerator.TONE_CDMA_CONFIRM

private fun playTone(tone: Int) {
    ToneGenerator(AudioManager.STREAM_MUSIC, 100).startTone(tone, 150)
}


// COMPLETE EFFECT RELATED
fun Context.positiveHaptics() {
    vibrateIfPossibleForSuccess()
    playTone(POSITIVE_TONE)
}

@Synchronized fun Context.negativeHaptics() {
    vibrateIfPossibleForError()
    playTone(NEGATIVE_TONE)
}