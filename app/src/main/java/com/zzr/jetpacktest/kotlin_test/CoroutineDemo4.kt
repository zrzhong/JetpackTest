package com.zzr.jetpacktest.kotlin_test

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/22
 */
fun main() {
    //    test1()
    val time = measureTimeMillis {
        runBlocking {

            val result = withContext(Dispatchers.IO) {
                delay(300)
                "Hello "
            }
            val result2 = withContext(Dispatchers.IO) {
                delay(400)
                "Kotlin"
            }
            val a = async(Dispatchers.IO) {
                log("a: ")
                delay(300)
                "Hello "
            }
            val b = async {
                log("b: ")
                delay(400)
                "Kotlin"
            }
//            log(result + result2)
            log(a.await() + b.await())
        }
    }
    log(time)

}

private suspend fun CoroutineScope.test1() {
    val job = launch {
        repeat(1000) {
            log("job: I'm sleeping $it")
            delay(500)
        }
    }
    delay(500)
    log("main: I'm tired of waiting!")
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    log("main: Now I can quit.")
}

private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")