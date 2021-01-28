package com.zzr.jetpacktest.kotlin_test

import kotlinx.coroutines.*

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/21
 */

fun main() = runBlocking {
    GlobalScope.launch {
        println("1")
        // 启动一个子协程
        val job = launch {
            println("2")
            try {// 捕获 协程cancel导致的异常，让代码继续往下执行
                delay(1000)
            } catch (e: Exception) {
                println("error: $e")
            }
            println("3")
            if (isActive) { // 如果协程cancel了，则isActive为false
                println("4")
            }
            delay(1000) // 没有捕获异常，则终止代码继续往下执行
            println("5")
        }
        delay(100)
        job.cancel()
    }
    delay(2000)

    /* val handler = CoroutineExceptionHandler { _, exception ->
         log("Caught $exception")
     }
     log("start")
     GlobalScope.launch {
         launch {
             delay(400)
             log("launch A")
         }
         launch {
             delay(300)
             log("launch B")
         }
         log("GlobalScope")
     }
     log("end")
     Thread.sleep(400)

     runBlocking(handler) {
         val job = launch {
             log("My job is ${coroutineContext[Job]}")
         }
         log("My job is $job")
         withContext(Dispatchers.IO){

         }
     }

     val job = GlobalScope.launch(handler) {
         throw AssertionError()
     }*/
}


private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")