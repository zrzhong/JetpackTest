package com.zzr.jetpacktest.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/29
 */
class LibraryA : Initializer<LibraryA.Dependency> {
    override fun create(context: Context): Dependency {
        // 初始化工作
        Log.e(TAG, "init LibraryA ")
        return Dependency()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

    companion object {
        private const val TAG = "LibraryA"
    }

    class Dependency {

    }
}