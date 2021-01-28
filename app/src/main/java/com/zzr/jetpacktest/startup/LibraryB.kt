package com.zzr.jetpacktest.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/29
 */
class LibraryB :Initializer<LibraryB.Dependency>{
    override fun create(context: Context): Dependency {
        // 初始化工作
        Log.e(TAG, "init LibraryB ")
        return Dependency()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf(LibraryA::class.java)
    }

    companion object {
        private const val TAG = "LibraryB"
    }

    class Dependency {

    }
}