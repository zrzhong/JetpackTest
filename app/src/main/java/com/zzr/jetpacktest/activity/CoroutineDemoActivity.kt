package com.zzr.jetpacktest.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import com.zzr.jetpacktest.databinding.ActivityCoroutineDemoBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.concurrent.thread

class CoroutineDemoActivity : BaseActivity<ActivityCoroutineDemoBinding>() {

    companion object {
        private const val TAG = "CoroutineDemoActivity"
    }

    private fun loadUser() {
        lifecycleScope.launchWhenResumed {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            createFlow()
                .collect {
                    Log.i(TAG, "onCreate: $it")
                }
        }

        binding.onceClick.setOnceClick {
            Log.i(TAG, "点击了button")
        }
    }

    private fun createFlow(): Flow<Int> {
        return flow {
            for (i in 1..10) {
                emit(i)
            }
        }
    }

    override fun onDestroy() {
        val thread = thread {
            Thread.sleep(1000)
            Log.i(TAG, "onDestroy: ${Thread.currentThread().name}")
        }
        super.onDestroy()
    }
}