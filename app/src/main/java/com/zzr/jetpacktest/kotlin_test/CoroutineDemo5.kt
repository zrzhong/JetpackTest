package com.zzr.jetpacktest.kotlin_test

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/23
 */

suspend fun main() {
    LogUtil.log(1)
    val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        LogUtil.log(2)
        delay(1000)
        LogUtil.log(3)
    }
    job.cancel()
    LogUtil. log(4)
    job.join()
}
