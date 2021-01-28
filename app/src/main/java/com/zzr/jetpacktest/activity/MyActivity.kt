package com.zzr.jetpacktest.activity

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * @Author zzr
 * @Desc 使一个类具备生命周期
 * @Date 2020/11/9
 */
class MyActivity : Activity(), LifecycleOwner {
    private lateinit var mLifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLifecycleRegistry = LifecycleRegistry(this)
        mLifecycleRegistry.currentState = Lifecycle.State.CREATED

    }

    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }
}