package com.zzr.jetpacktest.dsl

import android.content.Context
import android.media.MediaPlayer

class Player(){

    private lateinit var listener:ListenerBuilder

    fun registerListener(build:ListenerBuilder.()->Unit){
        listener = ListenerBuilder().also(build)
    }

    fun play(){
        val player = Player()

    }
    fun start(callback: PlayCallback){

    }

    inner class ListenerBuilder {
        //lambda表达式
        private var pause: ((String) -> Unit)? = null
        private var play: ((String) -> Unit)? = null
        private var finish: ((String) -> Unit)? = null

        fun setPause(action: (String) -> Unit) {
            pause = action
        }

        fun setPlay(action: (String) -> Unit) {
            play = action
        }

        fun setFinish(action: (String) -> Unit) {
            finish = action
        }
    }
}