package com.zzr.jetpacktest.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, param: WorkerParameters) : Worker(context, param) {
    override fun doWork(): Result {
        Log.i("SimpleWorker", "doWork: ")
        return Result.success()
    }
}