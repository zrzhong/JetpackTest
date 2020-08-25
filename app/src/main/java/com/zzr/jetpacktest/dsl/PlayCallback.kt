package com.zzr.jetpacktest.dsl

interface PlayCallback {
    fun onPlay(s: String)
    fun onPause(time: Long)
    fun onFinish()
}