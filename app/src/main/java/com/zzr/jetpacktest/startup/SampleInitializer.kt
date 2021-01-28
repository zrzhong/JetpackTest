package com.zzr.jetpacktest.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/29
 */
class SampleInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Log.i("SampleInitializer", "SampleInitializer create: ")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}