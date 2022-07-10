package com.useruser.followay.domain.utils

import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.AudioManager.RINGER_MODE_NORMAL

fun Context.getVolumeLevel(streamType: Int): Int {
    val audioManager = this.getSystemService(AUDIO_SERVICE) as AudioManager
    return audioManager.getStreamVolume(streamType)
}

fun Context.isPhoneRingtoneEnabled(): Boolean{
    val audioManager = this.getSystemService(AUDIO_SERVICE) as AudioManager
    return when(audioManager.ringerMode){
        RINGER_MODE_NORMAL -> true
        else -> false
    }
}