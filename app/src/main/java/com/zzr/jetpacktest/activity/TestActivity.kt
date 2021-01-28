package com.zzr.jetpacktest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zzr.jetpacktest.R
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*

class TestActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    companion object {
        const val TAG = "TestActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val intent = intent
        val content = "value==${intent.getStringExtra("param1")},${intent.getStringExtra("param2")}"
        tv_content.text = content

        launch {
            launchJob()
            val deferred = asyncDeferred().await()
            Log.i(TAG, "async开启协程的返回值$deferred")
        }

    }

    private suspend fun launchJob() = coroutineScope {
        launch {
            Log.i(TAG, "launch 开启协程")
            delay(100)
            Log.i(TAG, "launch 开启协程结束")
        }
    }

    private suspend fun asyncDeferred() = async {
        Log.i(TAG, "async 开启协程")
        delay(100)
        Log.i(TAG, "async 开启协程结束")
        return@async "Result"
    }
}