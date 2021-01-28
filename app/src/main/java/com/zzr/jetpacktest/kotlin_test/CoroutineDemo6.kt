package com.zzr.jetpacktest.kotlin_test

import com.zzr.jetpacktest.entity.User
import kotlinx.coroutines.*

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/24
 */
suspend fun main() {
    LogUtil.log(1)
    try {
        supervisorScope { //①
            LogUtil.log(2)
            val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                LogUtil.log("${coroutineContext[CoroutineName]} $throwable")
            }
            launch(exceptionHandler + CoroutineName("2")) { // ②
                LogUtil.log(3)
                launch(exceptionHandler + CoroutineName("3")) { // ③
                    LogUtil.log(4)
                    delay(100)
                    throw ArithmeticException("Hey!!")
                }
                LogUtil.log(5)
            }
            LogUtil.log(6)
            val job = launch { // ④
                LogUtil.log(7)
                try {
                    delay(1000)
                } catch (e: Exception) {
                    LogUtil.log("delay. $e")
                }

            }
            try {
                LogUtil.log(8)
                job.join()
                LogUtil.log("9")
            } catch (e: Exception) {
                LogUtil.log("10. $e")
            }
        }
        LogUtil.log(11)
    } catch (e: Exception) {
        LogUtil.log("12. $e")
    }
    LogUtil.log(13)
}

/**
 * 使挂起函数支持协程的取消  suspendCancellableCoroutine
 */
suspend fun getUser() = suspendCancellableCoroutine<User> { continuation ->

//    OkHttpCall<User>().await()
    continuation.invokeOnCancellation {

    }
}
